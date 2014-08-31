package de.justkile.zapfmaster2000.model

class Token {

	String token
	
	String googleCloudMessagingToken
	Account account
	User user
	Admin admin
	
	static constraints = {
		account nullable:true
		user nullable:true
		admin nullable:true	
		googleCloudMessagingToken nullable:true
	}
}
