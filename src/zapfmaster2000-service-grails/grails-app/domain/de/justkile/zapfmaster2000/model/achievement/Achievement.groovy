package de.justkile.zapfmaster2000.model.achievement

class Achievement {

    long id
    String name
    String imagePath
    String description

    static constraints = {
        id name: 'id'
    }
}
