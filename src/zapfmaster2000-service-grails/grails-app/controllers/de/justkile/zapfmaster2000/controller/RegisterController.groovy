package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.User
import de.justkile.zapfmaster2000.model.news.NewUserNews
import de.justkile.zapfmaster2000.services.AuthException
import de.justkile.zapfmaster2000.services.AuthService
import de.justkile.zapfmaster2000.services.RegistrationService
import grails.transaction.Transactional

class RegisterController {

    static responseFormats = ['json', 'xml']

    def authService
    def registrationService
    def newsService

    @Transactional
    def registerUser() {
        try {
            def account = authService.retrieveAccount(params.token)
            def sex
            switch (params.sex) {
                case 'm':
                    sex = User.Sex.MALE;
                    break;
                case 'f':
                    sex = User.Sex.FEMALE;
                    break;
                default:
                    response.sendError 400
                    return;
            }

            def user = registrationService.registerUser(account, params.userName, params.password, sex, params.weight.toInteger())
            def token = authService.loginUser(params.userName, params.password)
            def newUserNews = new NewUserNews(account: account, user: user)
            newsService.publishNews(newUserNews)
            render token
        } catch (AuthException e) {
            response.sendError 403
        }
    }

    @Transactional
    def registerRfidCard() {
        try {
            println "# $params"
            User user = authService.retrieveUser(params.token)
            user.rfidTag = params.rfid.toLong()
            user.save()
            respond status:'OK'
        } catch (AuthException e) {
            response.sendError 403
        }
    }
}
