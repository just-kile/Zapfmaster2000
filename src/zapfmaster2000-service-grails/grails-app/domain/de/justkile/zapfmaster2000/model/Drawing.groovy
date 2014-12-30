package de.justkile.zapfmaster2000.model

class Drawing {

    double amount
    Keg keg
    Account account
    Date date = new Date()

    static belongsTo = [user: User]
    static hasMany = [ticks: Ticks]

    static constraints = {
    }
}
