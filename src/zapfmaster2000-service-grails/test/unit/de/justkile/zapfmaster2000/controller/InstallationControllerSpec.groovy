


package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Admin;
import de.justkile.zapfmaster2000.model.Admin.AdminType;
import de.justkile.zapfmaster2000.model.Dummy;
import grails.test.mixin.Mock;
import grails.test.mixin.TestFor
import grails.transaction.Transactional;
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(InstallationController)
@Mock(Admin)
class InstallationControllerSpec extends Specification {

    void "test creation of initial admin"() {
		given:
			params.adminName = 'IAmTheAdmin'
			params.password = 'NobodyKnows'
		
		when:
			controller.createFirstAdmin()
		
		then:
			response.status == 200
			Admin.list().size() == 1
			def admin = Admin.list().first()
			admin.name == 'IAmTheAdmin' 
			admin.password == 'NobodyKnows'
    }
	

	void "test creation fails if admin already exists"() {
		given:
			new Admin(name:'as', password:'df', type:AdminType.ACCOUNT).save()
			
		when:
			controller.createFirstAdmin()
		
		then: 
			thrown Exception
	}
}
