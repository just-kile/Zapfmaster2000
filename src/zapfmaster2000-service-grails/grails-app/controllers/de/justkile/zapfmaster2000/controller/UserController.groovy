package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.User
import de.justkile.zapfmaster2000.services.AuthException
import grails.transaction.Transactional

class UserController {

    static responseFormats = ['json', 'xml']

    def authService

    @Transactional(readOnly = true)
    def retrieveUsers() {
        try {
            def loggedInAccount = authService.retrieveAccount(params.token)
            def users = User.withCriteria {
                accounts {
                    idEq(loggedInAccount.id)
                }
            }
            respond users.collect {
                [
                        userId   : it.id,
                        userName : it.name,
                        imagePath: it.imagePath,
                        rfidTag  : it.rfidTag,
                ]
            }
        } catch (AuthException e) {
            response.sendError 403
        }
    }
}
