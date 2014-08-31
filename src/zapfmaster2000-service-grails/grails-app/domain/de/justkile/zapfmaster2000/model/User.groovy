package de.justkile.zapfmaster2000.model

class User {

    long id
	String name
	String password
    String imagePath
    int weight
    long rfidTag = -1
    Sex sex
    Account account
    Type type = Type.USER

    static hasMany = [drawings: Drawing]
    static fetchMode = [drawings: 'eager']

	static constraints = {
        id name: 'id'
	}

    enum Sex {
        MALE, FEMALE
    }

    enum Type{
        USER, GUEST
    }
}
