package de.kile.zapfmaster2000.rest.impl.core.statistics;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.DrawCountUserListResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestUserRankingByDrawCountBuilder extends AbstractMockingTest {

	
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
	public void testDrawCountRanking() {
		DrawCountUserListResponse[] drawCountUserListResponses = RankingsBuilder
				.retrieveDrawCountUserListResponse(null, null,-1, account);
		assertEquals(3, drawCountUserListResponses.length);

		assertConforms(user3,
				(DrawCountUserListResponse) drawCountUserListResponses[0]);
		assertConforms(user1,
				(DrawCountUserListResponse) drawCountUserListResponses[1]);
	}

	@Test
	public void testDrawCountFrom() {
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(midDate);
		calFrom.add(Calendar.MINUTE, -1);

		DrawCountUserListResponse[] drawCountUserListResponses = RankingsBuilder
				.retrieveDrawCountUserListResponse(calFrom.getTime(), null,-1,
						account);

		assertEquals(3, drawCountUserListResponses.length);

		assertConforms(user1, drawCountUserListResponses[0]);
	}

	@Test
	public void testDrawCountFromTo() {

		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(midDate);
		calFrom.add(Calendar.MINUTE, -1);

		Calendar calTo = Calendar.getInstance();
		calTo.setTime(midDate2);
		calTo.add(Calendar.MINUTE, 1);

		DrawCountUserListResponse[] drawCountUserListResponses = RankingsBuilder
				.retrieveDrawCountUserListResponse(calFrom.getTime(),
						calTo.getTime(),-1, account);

		assertEquals(2, drawCountUserListResponses.length);

		// test only number of drawings, order of users is not important as
		// everybody has drawcount 1
		assertEquals(user1.getDrawings().size(),
				(drawCountUserListResponses[0]).getDrawCount());
	}

	public void assertConforms(User user, DrawCountUserListResponse response) {
		assertEquals(user.getName(), response.getName());
		assertEquals(user.getId(), response.getId());
		assertEquals(user.getImagePath(), response.getImage());
		assertEquals(user.getDrawings().size(), response.getDrawCount());
	}
	
	@Test
	public void testMax() {
		DrawCountUserListResponse[] drawCountUserListResponses = RankingsBuilder
				.retrieveDrawCountUserListResponse(null, null,1, account);
		assertEquals(1, drawCountUserListResponses.length);

		assertConforms(user3,
				(DrawCountUserListResponse) drawCountUserListResponses[0]);
	}

}
