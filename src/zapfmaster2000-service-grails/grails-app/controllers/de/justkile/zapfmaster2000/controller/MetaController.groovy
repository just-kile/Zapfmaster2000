package de.justkile.zapfmaster2000.controller

class MetaController {

    static responseFormats = ['json', 'xml']

    def retrieveVersion() {
        def version = [:]
        version.buildNumber = 'n/a'
        version.buildTime = 'n/a'
        version.pomVersion = 'n/a'
        version.isProductive = false
        respond version
    }
}
