package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

import static org.mockito.Matchers.anyString;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestDrinkProgress extends AbstractMockingTest {

	private Account account1;
	private Box box1;
	private Keg keg1;

	private User user1;
	private User user2;

	@Before
	public void setupData() {
		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101, Sex.MALE,
				85, UserType.USER, account1);
		user2 = createUser("Bettina", "img/user2", "user2-pw", 202, Sex.FEMALE,
				85, UserType.USER, account1);

		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		createDrawing(0.7, createDate(2012, 1, 1, 1, 0, 0), keg1, user1);
		createDrawing(0.3, createDate(2012, 1, 1, 1, 15, 0), keg1, user2);
		createDrawing(3.5, createDate(2012, 1, 1, 2, 0, 0), keg1, user1);
		createDrawing(3.0, createDate(2012, 1, 1, 4, 1, 0), keg1, user2);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(anyString())).thenReturn(account1);
		mockAuthService(authService);
	}

	@Test
	public void testSimple() {

		DrinkProgressResource drinkProgressResource = new DrinkProgressResource();

		Response response = drinkProgressResource.retrieveDrinkProgress(null,
				null, null, null, "60");
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		DrinkProgressResponse drinkProgressResponse = (DrinkProgressResponse) response
				.getEntity();

		assertEquals(4, drinkProgressResponse.getAmount().length);
		assertEquals(1.0, drinkProgressResponse.getAmount()[0]);
		assertEquals(0.0, drinkProgressResponse.getAmount()[2]);

		// TODO test time properly
		// assertEquals(createDate(2012, 1, 1, 1, 0, 0),
		// drinkProgressResponse.getFrom());
		assertEquals(60, drinkProgressResponse.getInterval());
	}

	@Test
	public void testUserSpecific() {
		DrinkProgressResource drinkProgressResource = new DrinkProgressResource();

		Response response = drinkProgressResource.retrieveDrinkProgress(null,
				Long.toString(user1.getId()), null, null, "60");
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		DrinkProgressResponse drinkProgressResponse = (DrinkProgressResponse) response
				.getEntity();

		assertEquals(2, drinkProgressResponse.getAmount().length);
		assertEquals(0.7, drinkProgressResponse.getAmount()[0]);

		// TODO test time properly
		// assertEquals(createDate(2012, 1, 1, 1, 0, 0),
		// drinkProgressResponse.getFrom());
		assertEquals(60, drinkProgressResponse.getInterval());

	}

	@Test
	public void testFromTo() {

		SimpleDateFormat df = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);

		DrinkProgressResource drinkProgressResource = new DrinkProgressResource();

		Response response = drinkProgressResource.retrieveDrinkProgress(null,
				null, df.format(createDate(2012, 1, 1, 1, 15, 0)),
				df.format(createDate(2012, 1, 1, 2, 0, 0)), null);
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		DrinkProgressResponse drinkProgressResponse = (DrinkProgressResponse) response
				.getEntity();

		assertEquals(2, drinkProgressResponse.getAmount().length);
		assertEquals(0.3, drinkProgressResponse.getAmount()[0]);

		// TODO test time properly
		// assertEquals(createDate(2012, 1, 1, 1, 0, 0),
		// drinkProgressResponse.getFrom());
		assertEquals(30, drinkProgressResponse.getInterval());

	}

}
