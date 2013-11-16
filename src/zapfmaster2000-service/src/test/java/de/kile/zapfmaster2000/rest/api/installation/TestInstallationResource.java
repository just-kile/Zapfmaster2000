package de.kile.zapfmaster2000.rest.api.installation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class TestInstallationResource extends AbstractMockingTest {

	@Test
	public void testNewInstallation() {
		InstallationResource resource = new InstallationResource();
		Response response = resource.retrieveStatus();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		StatusResponse statusResponse = (StatusResponse) response.getEntity();
		assertEquals("new", statusResponse.getStatus());
	}

	@Test
	public void testExistingInstallationNullAccount() {
		createAdmin("foo", "bar", null);

		InstallationResource resource = new InstallationResource();
		Response response = resource.retrieveStatus();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		StatusResponse statusResponse = (StatusResponse) response.getEntity();
		assertEquals("installed", statusResponse.getStatus());
	}

	@Test
	public void testExistingInstallationWithAccount() {
		Account admin = createAccount("acc");
		createAdmin("foo", "bar", admin);

		InstallationResource resource = new InstallationResource();
		Response response = resource.retrieveStatus();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		StatusResponse statusResponse = (StatusResponse) response.getEntity();
		assertEquals("installed", statusResponse.getStatus());
	}

	@Test
	public void testExistingInstallationMultipleAccounts() {
		Account admin = createAccount("acc");
		createAdmin("foo", "bar", admin);
		createAdmin("baz", "qux", null);

		InstallationResource resource = new InstallationResource();
		Response response = resource.retrieveStatus();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		StatusResponse statusResponse = (StatusResponse) response.getEntity();
		assertEquals("installed", statusResponse.getStatus());
	}

	@Test
	public void testCreateFirstAdminNewInstallation() {
		InstallationResource resource = new InstallationResource();
		
		Response response = resource.createFirstAdmin("foo", "bar");
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		String token = (String) response.getEntity();
		assertNotNull(token);
	}
	
	@Test
	public void testCreateFirstAdminExistingInstallation() {
		createAdmin("baz", "qux", null);
		
		InstallationResource resource = new InstallationResource();
		
		Response response = resource.createFirstAdmin("foo", "bar");
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
		
		assertNull(response.getEntity());
	}
}
