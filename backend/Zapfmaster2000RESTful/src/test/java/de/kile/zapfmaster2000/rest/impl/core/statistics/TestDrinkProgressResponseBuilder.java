package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.DrinkProgressResponse;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestDrinkProgressResponseBuilder extends AbstractMockingTest {
	private Account account1;
	private Box box1;
	private Keg keg1;

	private User user1;
	private User user2;
	private User userEmpty;

	private Date dFrom;
	private Date dTo;
	private Date dStart;

	private SimpleDateFormat sf = new SimpleDateFormat(
			PlatformConstants.DATE_TIME_FORMAT);

	@Before
	public void setupData() {
		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101, Sex.MALE,
				85, UserType.USER, account1);
		user2 = createUser("Bettina", "img/user2", "user2-pw", 202, Sex.FEMALE,
				85, UserType.USER, account1);
		userEmpty = createUser("Franz", "img/user2", "user2-pw", 302, Sex.MALE,
				85, UserType.USER, account1);

		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		dFrom = createDate(2012, 1, 1, 1, 15, 0);
		dTo = createDate(2012, 1, 1, 2, 1, 0);

		dStart = createDate(2012, 1, 1, 1, 0, 0);

		createDrawing(0.7, dStart, keg1, user1);
		createDrawing(0.3, dFrom, keg1, user2);
		createDrawing(3.5, dTo, keg1, user1);
		createDrawing(3.0, createDate(2012, 1, 1, 4, 1, 0), keg1, user2);
	}

	@Test
	public void testSimple() {

		DrinkProgressResponse drinkProgressResponse = DrinkProgressResponseBuilder
				.retrieveDrinkResponse(account1, -1, null, null, 60);

		assertEquals(4, drinkProgressResponse.getAmount().length);
		assertEquals(1.0, drinkProgressResponse.getAmount()[0]);
		assertEquals(0.0, drinkProgressResponse.getAmount()[2]);

		assertEquals(sf.format(dStart),
				drinkProgressResponse.getStartDate());
		assertEquals(60, drinkProgressResponse.getInterval());
	}

	@Test
	public void testUserSpecific() {
		DrinkProgressResponse drinkProgressResponse = DrinkProgressResponseBuilder
				.retrieveDrinkResponse(account1, user1.getId(), null, null, 60);

		assertEquals(2, drinkProgressResponse.getAmount().length);
		assertEquals(0.7, drinkProgressResponse.getAmount()[0]);

		assertEquals(sf.format(dStart),
				drinkProgressResponse.getStartDate());
		assertEquals(60, drinkProgressResponse.getInterval());

	}

	@Test
	public void testFromTo() {

		DrinkProgressResponse drinkProgressResponse = DrinkProgressResponseBuilder
				.retrieveDrinkResponse(account1, -1, dFrom, dTo);
		assertEquals(2, drinkProgressResponse.getAmount().length);
		assertEquals(0.3, drinkProgressResponse.getAmount()[0]);

		assertEquals(sf.format(dFrom), drinkProgressResponse.getStartDate());
		assertEquals(30, drinkProgressResponse.getInterval());

	}

	@Test
	public void testEmptyUser() {
		DrinkProgressResponse drinkProgressResponse = DrinkProgressResponseBuilder
				.retrieveDrinkResponse(account1, 666, null, null);

		assertEquals(1, drinkProgressResponse.getAmount().length);
		assertEquals(0.0, drinkProgressResponse.getAmount()[0]);

		assertEquals(30, drinkProgressResponse.getInterval());

		drinkProgressResponse = DrinkProgressResponseBuilder
				.retrieveDrinkResponse(account1, userEmpty.getId(), null, null);

		assertEquals(1, drinkProgressResponse.getAmount().length);
		assertEquals(0.0, drinkProgressResponse.getAmount()[0]);

		assertEquals(30, drinkProgressResponse.getInterval());

	}

}
