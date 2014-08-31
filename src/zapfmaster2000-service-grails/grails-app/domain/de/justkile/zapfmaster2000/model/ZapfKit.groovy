package de.justkile.zapfmaster2000.model

class ZapfKit {

    long id

	String name
	String passphrase
	Account account
	
	double a0
	double a1
	double a2
	
    static constraints = {
        id name: 'id'
    }
}
