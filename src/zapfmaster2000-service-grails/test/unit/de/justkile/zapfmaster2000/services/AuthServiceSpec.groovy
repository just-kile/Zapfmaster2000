
package de.justkile.zapfmaster2000.services

import de.justkile.zapfmaster2000.model.Admin;
import de.justkile.zapfmaster2000.model.Token;
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AuthService)
@Mock([Admin, Token])
class AuthServiceSpec extends Specification {

    void "test login to existing admin"() {
		given:
			new Admin(name: 'n', password: 'pw', type: Admin.AdminType.GLOBAL).save()
		when:
			def token = service.loginAdmin('n', 'pw')
		then:
			token
			Token.list().size == 1
			Token.list().first().token == token
    }
	
	void "test login to admin failure"() {
		when:
			service.loginAdmin('n', 'pw')
		then:
			thrown AuthException
	}
	
	void "test retrieve logged in admin"() {
		given:
			def admin = new Admin(name: 'n', password: 'pw', type: Admin.AdminType.GLOBAL).save()
			new Token(token:'abc', admin: admin).save()
		when:
			def retrievedAdmin = service.retrieveAdmin('abc')
		then:
			admin == retrievedAdmin
	}
}
