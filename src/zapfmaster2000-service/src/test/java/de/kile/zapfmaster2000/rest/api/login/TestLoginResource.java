package de.kile.zapfmaster2000.rest.api.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestLoginResource extends AbstractMockingTest {

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

	@Test
	public void testSetupGcm() {
		AuthService authServiceMock = mock(AuthService.class);
		mockAuthService(authServiceMock);

		String zm2kToken = "token";
		String gcmToken = "gcm";
		new LoginResource().setupGcm(zm2kToken, gcmToken);
		verify(authServiceMock).setupGoogleCloudMessagingToken(zm2kToken,
				gcmToken);
		verifyNoMoreInteractions(authServiceMock);
	}

}
