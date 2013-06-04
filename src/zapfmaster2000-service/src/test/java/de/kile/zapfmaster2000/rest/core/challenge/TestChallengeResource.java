package de.kile.zapfmaster2000.rest.core.challenge;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.junit.Assert.*;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.challenge.ChallengeResource;
import de.kile.zapfmaster2000.rest.api.challenge.LoggedInUserReponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.impl.core.push.ChallengeRequestResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestChallengeResource extends AbstractMockingTest {

	@Before
	public void setup() {
		Account account = createAccount("Test");
		Box box = createBox("box", "", "", account);
		Keg keg = createKeg("", new Date(), null, 50, box);

		User user1 = createUser("u1", "i1", "", 0, Sex.MALE, 0, UserType.USER,
				account);
		User user2 = createUser("u2", "i2", "", 0, Sex.MALE, 0, UserType.USER,
				account);
		User user3 = createUser("u3", "i3", "", 0, Sex.MALE, 0, UserType.USER,
				account);
		User user4 = createUser("u4", "i4", "", 0, Sex.MALE, 0, UserType.USER,
				account);

		createDrawing(0.2, new Date(), keg, user1);
		createDrawing(0.3, new Date(), keg, user1);
		createDrawing(0.4, new Date(), keg, user1);
		createDrawing(0.5, new Date(), keg, user2);

		ChallengeService challengeServiceMock = mock(ChallengeService.class);
		when(challengeServiceMock.retrieveLoggedInUsers(any(Account.class)))
				.thenReturn(Arrays.asList(user1, user2, user3, user4));
		mockChallengeService(challengeServiceMock);

		AuthService authServiceMock = mock(AuthService.class);
		when(authServiceMock.retrieveUser(any(String.class))).thenReturn(user4);
		mockAuthService(authServiceMock);
	}

	@Test
	public void testRetrieveUsers() {
		Response response = new ChallengeResource().retrieveUsers(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<LoggedInUserReponse> users = (List<LoggedInUserReponse>) response
				.getEntity();
		assertEquals(3, users.size()); // user 4 performs the request and thus
										// should not be part of the response.

		assertResponse(users.get(0), "u1", "i1", 0.9);
		assertResponse(users.get(1), "u2", "i2", 0.5);
		assertResponse(users.get(2), "u3", "i3", 0.0);
	}

	private void assertResponse(LoggedInUserReponse response, String userName,
			String imagePath, double amount) {
		assertEquals(userName, response.getUserName());
		assertEquals(imagePath, response.getUserImage());
		assertEquals(amount, response.getTotalAmount(), 0.01);
	}
}
