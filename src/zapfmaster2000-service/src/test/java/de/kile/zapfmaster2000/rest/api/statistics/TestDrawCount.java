package de.kile.zapfmaster2000.rest.api.statistics;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

import static org.mockito.Matchers.anyString;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDrawCount extends AbstractMockingTest {
	private Account account1;
	private Box box1;
	private Keg keg1;
	private User user2;

	@Before
	public void setupData() {

		truncate();

		account1 = createAccount("foo-account");

		User user1 = createUser("Torsten", "img/user1", "user1-pw", 101,
				Sex.MALE, 85, UserType.USER, account1);
		user2 = createUser("Bettina", "img/user2", "user2-pw", 202, Sex.FEMALE,
				85, UserType.USER, account1);

		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		createDrawing(1, createDate(2012, 1, 1, 1, 0, 0), keg1, user1);
		createDrawing(2, createDate(2012, 2, 1, 2, 0, 0), keg1, user2);
		createDrawing(2, createDate(2012, 3, 1, 2, 0, 0), keg1, user2);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(anyString())).thenReturn(account1);
		mockAuthService(authService);
	}

	@Test
	public void testSimple() {
		DrawCountResource drawCountResource = new DrawCountResource();

		Response response = drawCountResource.retrieveDrawCount(null, null);
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		DrawCountResponse drawCountResponse = (DrawCountResponse) response
				.getEntity();

		assertEquals(3, drawCountResponse.getCount());
		assertEquals(1.5, drawCountResponse.getAverageOperationsPerHour());
	}

	@Test
	public void testUserSpecific() {
		DrawCountResource drawCountResource = new DrawCountResource();

		Response response = drawCountResource.retrieveDrawCount(null,
				String.valueOf(user2.getId()));
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		DrawCountResponse drawCountResponse = (DrawCountResponse) response
				.getEntity();

		assertEquals(2, drawCountResponse.getCount());
		assertEquals(2.0, drawCountResponse.getAverageOperationsPerHour());
	}
}