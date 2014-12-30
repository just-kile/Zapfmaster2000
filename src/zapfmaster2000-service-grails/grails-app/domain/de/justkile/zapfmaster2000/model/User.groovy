package de.justkile.zapfmaster2000.model

class User {

    long id
	String name
	String password
    String imagePath
    int weight
    long rfidTag = -1
    Sex sex
    Type type = Type.USER

    static hasMany = [
            drawings: Drawing,
            accounts: Account
    ]

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
