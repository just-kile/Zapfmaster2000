package de.justkile.zapfmaster2000.services

import grails.plugin.cache.web.filter.ExpressionEvaluator

/**
 * Created by thomas on 25.08.14.
 */
class RegistrationException extends Exception {

    RegistrationException(String message) {
        super(message)
    }
}
