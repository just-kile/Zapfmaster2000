package de.kile.zapfmaster2000.rest.api.login;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestLoginResource extends AbstractDatabaseTest {

	@Before
	public void createEntities() {
		Account account = createAccount("account1");
		createBox("pw", "loc", "1", account);
		createUser("Uschi", "Me.jpg", "Ralf", 123, Sex.FEMALE, 0,
				UserType.USER, account);
	}

	@Test
	public void userCorrectCredentials() {
		LoginResource login = new LoginResource();

		Response response = login.loginUser("Uschi", "Ralf");
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	public void userInvalidCredentials() {
		LoginResource login = new LoginResource();

		Response response = login.loginUser("Uschi", "Dieter");
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
				response.getStatus());
	}

}
