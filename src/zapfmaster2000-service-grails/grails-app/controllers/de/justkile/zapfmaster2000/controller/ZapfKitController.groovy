package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Account
import de.justkile.zapfmaster2000.model.Admin
import de.justkile.zapfmaster2000.model.ZapfKit
import de.justkile.zapfmaster2000.services.AuthException
import de.justkile.zapfmaster2000.services.AuthService
import de.justkile.zapfmaster2000.services.KegService
import de.justkile.zapfmaster2000.services.ZapfKitService
import de.justkile.zapfmaster2000.utils.zapfing.ZapfService
import grails.transaction.Transactional

import static grails.async.Promises.tasks

class ZapfKitController {

    static responseFormats = ['json', 'xml']

    AuthService authService
    ZapfKitService zapfKitService
    KegService kegService

    @Transactional(readOnly = true)
    def retrieveZapfKitsForAccount() {
        try {
            def account
            try {
                account = authService.retrieveAccount(params.token)
            } catch (e) {
                // not logged in as account
            }
            try {
                def admin = authService.retrieveAdmin(params.token)
                if (admin.type == Admin.AdminType.ACCOUNT) {
                    account = admin.account
                }
            } catch (e) {
                // not logged in as admin
            }
            if (account) {
                respond retrieveZapfKitsInternal(account.id)
            } else {
                response.sendError 403
            }
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    @Transactional(readOnly = true)
    def retrieveZapfKits() {
        try {
            def admin = authService.retrieveAdmin(params.token)
            respond retrieveZapfKitsInternal(params.accountId)
        } catch (e) {
            response.sendError 403
        }
    }

    @Transactional
    def createZapfKit() {
        try {
            def admin = authService.retrieveAdmin(params.token)
            def account = Account.get(params.accountId)
            def a0 = params.a0 ? params.a0 : 0.00006186472614225462
            def a1 = params.a1 ? params.a1 : 0.0000825562566780224
            def a2 = params.a2 ? params.a2 : -1.675383403699784e-8
            println 'asdfasdf'
            if (account && params.location && params.passphrase) {
                println 'asdfasdf pre 1'
                def zapfKit = new ZapfKit(account: account, name: params.location, passphrase: params.passphrase, a0: a0, a1: a1, a2: a2)
                println '{REE!!!'
                println zapfKitService
                zapfKitService.createZapfKit(zapfKit)
                respond status: 'OK'
            } else {
                response.sendError 400
            }
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    @Transactional(readOnly = true)
    def retrieveRfidTagAsync() {
        try {
            def account = authService.retrieveAccount(params.token)
            def zapfKit = ZapfKit.get(params.zapfKitId)

            if (zapfKit && zapfKit.account == account) {
                tasks { render zapfKitService.retrieveNextRfidTag(zapfKit) }
            } else {
                response.sendError 403
            }
        } catch (e) {
            response.sendError 403
        }
    }

    def processRfidTag() {
        def zapfKit = ZapfKit.findByPassphrase(params.passphrase)
        if (zapfKit) {
            zapfKitService.processRfidTag(zapfKit, params.rfid.toLong())
            respond status: 'OK'
        } else {
            response.sendError 400
        }
    }

    @Transactional
    def performDrawing() {
        def zapfKit = ZapfKit.findByPassphrase(params.passphrase)
        if (zapfKit) {
            def totalAmount = zapfKitService.retrieveZapfService(zapfKit).draw(params.ticks.toInteger())
            respond totalAmount: totalAmount
        } else {
            response.sendError 400
        }
    }

    @Transactional
    def switchKeg() {
        try {
            def admin = authService.retrieveAdmin(params.token)
            def zapfKit = ZapfKit.get(params.zapfKitId)
            if (admin.account == zapfKit.account) {
                kegService.switchKeg(zapfKit, params.brand, params.size().toInteger())
                respond status: 'OK'
            } else {
                response.sendError 403
            }
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    private def retrieveZapfKitsInternal(accountId) {
        def kits = []
        ZapfKit.where { account.id == accountId }.each {
            kits << ['boxId': it.id, 'name': it.name, 'passphrase': it.passphrase]
        }
        kits
    }
}
