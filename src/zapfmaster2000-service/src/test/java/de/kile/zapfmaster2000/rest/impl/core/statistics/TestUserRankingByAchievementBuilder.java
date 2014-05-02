package de.kile.zapfmaster2000.rest.impl.core.statistics;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.AchievementUserListResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestUserRankingByAchievementBuilder extends AbstractMockingTest {

	private Account account;
	private Account account2;

	private User user1;
	private User user2;
	private User user3;
	private User userForeignAcc;

	private Achievement achievement1;
	private Achievement achievement2;

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

		// Achievements
		achievement1 = createAchievement("achievement-1", "desc1", "img/ach1");
		achievement2 = createAchievement("achievement-2", "desc2", "img/ach2");

		createGainedAchievement(midDate, user1, achievement1);
		createGainedAchievement(midDate2, user2, achievement2);
		createGainedAchievement(tooEarly, user3, achievement1);
		createGainedAchievement(tooLate, user3, achievement2);
		createGainedAchievement(midDate, userForeignAcc, achievement1);
	}

	@Test
	public void testAchievementList() {

		AchievementUserListResponse[] achievementUserListResponses = new RankingsBuilderImpl()
				.retrieveAchievementUserListResponse(null, null, -1, account);

		assertEquals(3, achievementUserListResponses.length);

		assertConforms(user3, achievementUserListResponses[0]);

		// user 1 and 2 both have one achievement: Expect order by name!
		assertConforms(user1, achievementUserListResponses[1]);
		assertConforms(user2, achievementUserListResponses[2]);
	}

	@Test
	public void testAchievementListFrom() {
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(midDate);
		calFrom.add(Calendar.MINUTE, -1);

		AchievementUserListResponse[] achievementUserListResponses = new RankingsBuilderImpl()
				.retrieveAchievementUserListResponse(calFrom.getTime(), null,
						-1, account);

		assertEquals(3, achievementUserListResponses.length);

		// test only number of drawings, order of users is not important as
		// everybody has achievement count 1
		assertEquals(user1.getGained().size(),
				achievementUserListResponses[0].getCount());
	}

	@Test
	public void testAchievementListFromTo() {
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(midDate);
		calFrom.add(Calendar.MINUTE, -1);

		Calendar calTo = Calendar.getInstance();
		calTo.setTime(midDate2);
		calTo.add(Calendar.MINUTE, 1);

		AchievementUserListResponse[] achievementUserListResponses = new RankingsBuilderImpl()
				.retrieveAchievementUserListResponse(calFrom.getTime(),
						calTo.getTime(), -1, account);

		assertEquals(2, achievementUserListResponses.length);

		// test only number of drawings, order of users is not important as
		// everybody has achievement 1
		assertEquals(user1.getGained().size(),
				achievementUserListResponses[0].getCount());
	}

	@Test
	public void testMax() {

		AchievementUserListResponse[] achievementUserListResponses = new RankingsBuilderImpl()
				.retrieveAchievementUserListResponse(null, null, 1, account);

		assertEquals(1, achievementUserListResponses.length);

		assertConforms(user3, achievementUserListResponses[0]);
	}

	public void assertConforms(User user, AchievementUserListResponse response) {
		assertEquals(user.getName(), response.getName());
		assertEquals(user.getId(), response.getId());
		assertEquals(user.getImagePath(), response.getImage());
		assertEquals(user.getGained().size(), response.getCount());
	}

}
