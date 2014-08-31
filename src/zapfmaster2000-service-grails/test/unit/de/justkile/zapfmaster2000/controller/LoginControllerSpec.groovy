
package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Admin
import de.justkile.zapfmaster2000.model.Admin.AdminType;
import de.justkile.zapfmaster2000.model.Token
import de.justkile.zapfmaster2000.services.AuthException
import de.justkile.zapfmaster2000.services.AuthService
import grails.converters.JSON
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([Admin, Token])
class LoginControllerSpec extends Specification {

	AuthService authService = Mock()
	
	def setup() {
		controller.authService = authService
	}
	
    void "test login admin valid"() {
		given:
			params.adminName = 'adminName'
			params.password = 'password'
			authService.loginAdmin('adminName','password') >> 'abc123'
		when:
			controller.loginAdmin()
		then:
			response.text == 'abc123'
    }
	
	void "test login admin invalid credentials"() {
		given:
			authService.loginAdmin(_,_) >> { throw new AuthException() }
		when:
			controller.loginAdmin()
		then:
			response.status == 403
	}
	
	void "test retrieve login status valid admin"() {
		given:
			def admin = new Admin(name: name, type:type)
			params.token = 'theToken'
			authService.retrieveAdmin('theToken') >> admin
		when:
			def loginStatus = controller.retrieveLoginStatus()
		then:
			response.text == ([adminName:name, type:rawType] as JSON).toString()
		where:
			name		| type				| rawType
			'admin1'	| AdminType.GLOBAL	| 'global'
			'foo'		| AdminType.ACCOUNT | 'account'
	}
	
}
