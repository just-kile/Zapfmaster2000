package de.kile.zapfmaster2000.rest.impl.core.statistics;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.RankResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static org.junit.Assert.assertEquals;

public class TestRankResponseBuilder extends AbstractMockingTest {
	private Account account;

	private Box box;

	private Keg keg;

	private User user1;
	private User user2;
	private User user3;
	private User userEmpty;

	@Before
	public void setupData() {
		truncate();

		account = createAccount("foo-account");

		user1 = createUser("Horst", "/imagePath/image.jpg", "user-pw", 101,
				Sex.MALE, 85, UserType.USER, account);
		user2 = createUser("Ingrid", "/imagePath/image.jpg", "user-pw", 201,
				Sex.FEMALE, 85, UserType.USER, account);
		user3 = createUser("Waldemar", "/imagePath/image.jpg", "user-pw", 301,
				Sex.MALE, 85, UserType.USER, account);
		userEmpty = createUser("Franz", "/imagePath/image.jpg", "user-pw", 401,
				Sex.MALE, 85, UserType.USER, account);

		box = createBox("123", "home", "0.1", account);

		keg = createKeg("TestBier", null, null, 50, box);

		Achievement achievement1 = createAchievement("achievement1",
				"ach-description", "image/path/img.jpg");
		Achievement achievement2 = createAchievement("achievement2",
				"ach-description", "image/path/img.jpg");

		createGainedAchievement(null, user1, achievement1);
		createGainedAchievement(null, user2, achievement1);
		createGainedAchievement(null, user1, achievement2);

		createDrawing(5.14, null, keg, user1);
		createDrawing(2.71, null, keg, user2);
		createDrawing(4.1, null, keg, user3);
		createDrawing(6, null, keg, user3);
	}

	@Test
	public void testSimple() {

		RankResponse rankResponse = RankBuilder.retrieveRank(user1.getId(),
				account);

		assertEquals(2, rankResponse.getAmount());
		assertEquals(2, rankResponse.getDrawCount());
		assertEquals(1, rankResponse.getAchievements());

	}

	@Test
	public void testEmpty() {
		RankResponse rankResponse = RankBuilder.retrieveRank(userEmpty.getId(),
				account);

		assertEquals(4, rankResponse.getAmount());
		assertEquals(4, rankResponse.getDrawCount());
		assertEquals(3, rankResponse.getAchievements());
		
		
		//NonExistent
		rankResponse = RankBuilder.retrieveRank(666,
				account);

		assertEquals(4, rankResponse.getAmount());
		assertEquals(4, rankResponse.getDrawCount());
		assertEquals(3, rankResponse.getAchievements());
	}
}
