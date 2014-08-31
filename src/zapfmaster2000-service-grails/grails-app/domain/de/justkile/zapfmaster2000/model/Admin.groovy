package de.justkile.zapfmaster2000.model

class Admin {

	String name
	String password
	AdminType type
    Account account

    static constraints = {
        account nullable: true
    }

	enum AdminType {
		GLOBAL, ACCOUNT
	}
}
