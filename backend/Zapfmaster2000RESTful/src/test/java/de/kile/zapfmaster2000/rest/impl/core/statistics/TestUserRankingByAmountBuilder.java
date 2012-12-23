package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.UserAmountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static org.junit.Assert.assertEquals;

public class TestUserRankingByAmountBuilder extends AbstractMockingTest {

	private Account account;
	private Account account2;

	private Box box;
	private Box box2;
	private Keg keg;
	private Keg keg2;

	private User user1;
	private User user2;
	private User user3;
	private User userForeignAcc;

	private Date tooEarly;
	private Date midDate;
	private Date midDate2;
	private Date tooLate;

	@Before
	public void setupData() {
		truncate();

		account = createAccount("foo-account");

		account2 = createAccount("bar-account");

		// test users
		// number2 in ranking
		user1 = createUser("Horst", "/imagePath/image.jpg", "user-pw", 101,
				Sex.MALE, 85, UserType.USER, account);
		user2 = createUser("Ingrid", "/imagePath/image.jpg", "user-pw", 201,
				Sex.FEMALE, 85, UserType.USER, account);
		user3 = createUser("Waldemar", "/imagePath/image.jpg", "user-pw", 301,
				Sex.MALE, 85, UserType.USER, account);

		// Users that should not appear anywhere
		userForeignAcc = createUser("Judas", "/imagePath/image.jpg", "user-pw",
				101, Sex.MALE, 85, UserType.USER, account2);
		createUser("Wilfried", "/imagePath/image.jpg", "user-pw", 401,
				Sex.MALE, 85, UserType.USER, account);

		tooEarly = createDate(2011, 1, 1, 11, 0, 0);
		tooLate = createDate(2012, 1, 1, 14, 0, 0);

		midDate = createDate(2012, 1, 1, 11, 0, 0);
		midDate2 = createDate(2012, 1, 1, 12, 0, 0);

		box = createBox("123", "home", "0.1", account);
		box2 = createBox("123", "home", "0.1", account2);

		keg = createKeg("TestBier", tooEarly, null, 50, box);
		keg2 = createKeg("TestBier", tooEarly, null, 50, box2);

		// test drawings
		createDrawing(5.14, midDate, keg, user1);
		createDrawing(2.71, midDate2, keg, user2);
		createDrawing(4.1, tooEarly, keg, user3);
		createDrawing(6, tooLate, keg, user3);
		createDrawing(20, midDate, keg2, userForeignAcc);
	}

	@Test
	public void testAmountRanking() {
		UserAmountResponse[] userAmountResponses = RankingsBuilder
				.retrieveUserAmountResponse(null, null, -1, account);
		assertEquals(3, userAmountResponses.length);

		assertConforms(user3, userAmountResponses[0]);
		assertConforms(user1, userAmountResponses[1]);
	}

	@Test
	public void testAmountFrom() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(midDate);
		cal.add(Calendar.MINUTE, -1);

		UserAmountResponse[] userAmountResponses = RankingsBuilder
				.retrieveUserAmountResponse(cal.getTime(), null, -1, account);

		assertEquals(3, userAmountResponses.length);
		assertConforms(user3, userAmountResponses[0]);
	}

	@Test
	public void testAmountFromTo() {
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(midDate);
		calFrom.add(Calendar.MINUTE, -1);

		Calendar calTo = Calendar.getInstance();
		calTo.setTime(midDate2);
		calTo.add(Calendar.MINUTE, 1);

		UserAmountResponse[] userAmountResponses = RankingsBuilder
				.retrieveUserAmountResponse(calFrom.getTime(), calTo.getTime(),
						-1, account);

		assertEquals(2, userAmountResponses.length);

		// user3 should not be first because his drawings at in the wrong time
		assertConforms(user1, userAmountResponses[0]);
	}
	
	@Test
	public void testMax() {
		UserAmountResponse[] userAmountResponses = RankingsBuilder
				.retrieveUserAmountResponse(null, null, 1, account);
		assertEquals(1, userAmountResponses.length);

		assertConforms(user3, userAmountResponses[0]);
	}

	public void assertConforms(User user, UserAmountResponse response) {
		assertEquals(user.getName(), response.getName());
		assertEquals(user.getId(), response.getId());
		assertEquals(user.getImagePath(), response.getImage());
	}

}
