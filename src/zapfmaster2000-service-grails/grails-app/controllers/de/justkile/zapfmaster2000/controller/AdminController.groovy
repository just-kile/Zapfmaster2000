package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Account
import de.justkile.zapfmaster2000.model.Admin
import de.justkile.zapfmaster2000.services.AuthService
import grails.transaction.Transactional

class AdminController {

    static responseFormats = ['json', 'xml']
    AuthService authService

    @Transactional(readOnly = true)
    def retrieveAccountAdmins() {
        try {
            def admin = authService.retrieveAdmin(params.token)
            if (admin && admin.type == Admin.AdminType.GLOBAL) {
                def admins = Admin.list()
                def response = []
                admins.each {
                    def adminResponse = [adminId: it.id, adminName: it.name]
                    adminResponse.type = it.type.toString().toLowerCase()
                    if (it.account) {
                        adminResponse.accountName = it.account.name
                        adminResponse.accountId = it.account.id
                    }
                    response << adminResponse
                }
                respond response
            } else {
                response.sendError 403
            }
        } catch (e) {
            response.sendError 403
        }

    }

    def createAccountAdmin() {
        try {
            def admin = authService.retrieveAdmin(params.token)
            if (admin && (admin.type == Admin.AdminType.GLOBAL || admin.account?.id == params.accountId)) {
                def account = Account.get(params.accountId)
                if (account) {
                    new Admin(name: params.adminName, password: params.password, account: account, type: Admin.AdminType.ACCOUNT).save()
                    respond status: 'OK'
                } else {
                    response.sendError 403
                }
            } else {
                response.sendError 403
            }
        } catch (e) {
            response.sendError 403
        }
    }
}
