package de.justkile.zapfmaster2000.model

class Keg {

    long id
    String brand
    ZapfKit zapfKit
    int size
    Date startDate = new Date()
    Date endDate

    static constraints = {
        id name: 'id'
        endDate nullable: true
    }
}
