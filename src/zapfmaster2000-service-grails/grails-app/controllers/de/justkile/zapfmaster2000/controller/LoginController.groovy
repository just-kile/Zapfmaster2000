package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.services.AuthException
import grails.transaction.Transactional;
import de.justkile.zapfmaster2000.services.AuthService

class LoginController {

	static responseFormats = ['json', 'xml']
	AuthService authService
	
	@Transactional
    def loginAdmin() {
		try {
			def token = authService.loginAdmin(params.adminName, params.password)
			render token
		} catch (e) {
			response.sendError(403)
		}	
	}

    @Transactional
    def loginAccount() {
        try {
            def token = authService.loginAccount(params.accountName)
            render token
        } catch (e) {
            response.sendError 403
        }
    }

    @Transactional
    def loginUser() {
        try {
            def token = authService.loginUser(params.userName, params.password)
            render token
        } catch(AuthException e) {
            reponse.sendError 403
        }
    }
	
	@Transactional(readOnly = true)
	def retrieveLoginStatus() {
		try {
			def admin = authService.retrieveAdmin(params.token)
			respond (adminName:admin.name, type:admin.type.toString().toLowerCase())
		} catch (e) {
			response.sendError(403)
		}
	}
}
