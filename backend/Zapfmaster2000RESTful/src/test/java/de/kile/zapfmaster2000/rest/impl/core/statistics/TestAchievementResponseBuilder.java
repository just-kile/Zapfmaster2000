package de.kile.zapfmaster2000.rest.impl.core.statistics;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.AchievementResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestAchievementResponseBuilder extends AbstractMockingTest {
	private Account account1;
	private User user1;

	@Before
	public void setupData() {
		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101, Sex.MALE,
				85, UserType.USER, account1);
		User user2 = createUser("Bettina", "img/user2", "user2-pw", 202,
				Sex.FEMALE, 85, UserType.USER, account1);

		Achievement achievement1 = createAchievement("achievement1",
				"ach-description", "image/path/img.jpg");
		Achievement achievement2 = createAchievement("achievement2",
				"ach-description", "image/path/img.jpg");

		createGainedAchievement(createDate(2012, 1, 4, 1, 0, 0), user1,
				achievement1);
		createGainedAchievement(createDate(2012, 2, 5, 2, 0, 0), user2,
				achievement1);
		createGainedAchievement(createDate(2012, 3, 6, 2, 0, 0), user1,
				achievement2);
	}

	@Test
	public void simpleTest() {

		AchievementResponse achievementResponse = AchievementResponseBuilder
				.retrieveAchievementResponse(-1, account1);


		assertEquals(3, achievementResponse.getCount());
		assertEquals(2, achievementResponse.getMostAchievementHour());
	}

	@Test
	public void testUserSpecific() {
		AchievementResponse achievementResponse = AchievementResponseBuilder
				.retrieveAchievementResponse(user1.getId(), account1);

		assertEquals(2, achievementResponse.getCount());
	}
	
	@Test
	public void testUserNotFound(){
		AchievementResponse achievementResponse = AchievementResponseBuilder
				.retrieveAchievementResponse(666, account1);
		assertEquals(-1,achievementResponse.getMostAchievementHour());
	}
}
