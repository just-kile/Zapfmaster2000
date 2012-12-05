package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the method retrieveUserRankingByAchievementCount in the class
 * {@link RankingsResource}. Different users, multiple {@link Drawing}, multiple
 * {@link Account}
 * 
 * @author PB
 * 
 */
public class TestUserRankingByAchievementCount extends AbstractMockingTest {

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
	private String fromString;
	private String toString;

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

		SimpleDateFormat df = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);

		tooEarly = createDate(2011, 1, 1, 11, 0, 0);
		tooLate = createDate(2012, 1, 1, 14, 0, 0);

		midDate = createDate(2012, 1, 1, 11, 0, 0);
		midDate2 = createDate(2012, 1, 1, 12, 0, 0);

		Calendar cal = Calendar.getInstance();

		cal.setTime(midDate);
		cal.add(Calendar.MINUTE, -1);
		fromString = df.format(cal.getTime());

		cal.setTime(midDate2);
		cal.add(Calendar.MINUTE, 1);
		toString = df.format(cal.getTime());

		// Achievements
		achievement1 = createAchievement("achievement-1", "desc1", "img/ach1");
		achievement2 = createAchievement("achievement-2", "desc2", "img/ach2");

		createGainedAchievement(midDate, user1, achievement1);
		createGainedAchievement(midDate2, user2, achievement2);
		createGainedAchievement(tooEarly, user3, achievement1);
		createGainedAchievement(tooLate, user3, achievement2);
		createGainedAchievement(midDate, userForeignAcc, achievement1);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(null)).thenReturn(account);
		mockAuthService(authService);

	}

	@Test
	public void testAchievementList() {
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource
				.retrieveUserRankingByAchievementCount(null, null, null);

		Object[] rawAchievementListResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawAchievementListResponse.length);

		assertConforms(user3,
				(AchievementUserListResponse) rawAchievementListResponse[0]);
		assertConforms(user1,
				(AchievementUserListResponse) rawAchievementListResponse[1]);
	}

	@Test
	public void testAchievementListFrom() {
		RankingsResource rankingsResource = new RankingsResource();

		Response response = rankingsResource
				.retrieveUserRankingByAchievementCount(fromString, null, null);

		Object[] rawAchievementListResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawAchievementListResponse.length);

		// test only number of drawings, order of users is not important as
		// everybody has achievement count 1
		assertEquals(user1.getGained().size(),
				((AchievementUserListResponse) rawAchievementListResponse[0])
						.getCount());
	}

	@Test
	public void testAchievementListFromTo() {
		RankingsResource rankingsResource = new RankingsResource();

		Response response = rankingsResource
				.retrieveUserRankingByAchievementCount(fromString, toString,
						null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Object[] rawAchievementListResponse = (Object[]) response.getEntity();

		assertEquals(2, rawAchievementListResponse.length);

		// test only number of drawings, order of users is not important as
		// everybody has achievement 1
		assertEquals(user1.getGained().size(),
				((AchievementUserListResponse) rawAchievementListResponse[0])
						.getCount());
	}

	public void assertConforms(User user, AchievementUserListResponse response) {
		assertEquals(user.getName(), response.getName());
		assertEquals(user.getId(), response.getId());
		assertEquals(user.getImagePath(), response.getImage());
		assertEquals(user.getGained().size(), response.getCount());
	}

}
