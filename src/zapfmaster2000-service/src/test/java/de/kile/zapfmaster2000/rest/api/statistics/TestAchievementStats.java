package de.kile.zapfmaster2000.rest.api.statistics;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestAchievementStats extends AbstractMockingTest {

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

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(anyString())).thenReturn(account1);
		mockAuthService(authService);
	}

	@Test
	public void simpleTest() {
		AchievementResource achievementResource = new AchievementResource();

		Response response = achievementResource.retrieveAchievementStats(null,
				null);
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		AchievementResponse achievementResponse = (AchievementResponse) response
				.getEntity();

		assertEquals(3, achievementResponse.getCount());
		assertEquals(2, achievementResponse.getMostAchievementHour());
	}

	@Test
	public void testUserSpecific() {
		AchievementResource achievementResource = new AchievementResource();

		Response response = achievementResource.retrieveAchievementStats(null,
				String.valueOf(user1.getId()));
		assertEquals(response.getStatus(), Status.OK.getStatusCode());

		AchievementResponse achievementResponse = (AchievementResponse) response
				.getEntity();

		assertEquals(2, achievementResponse.getCount());
	}
}
