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
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestRankResource extends AbstractMockingTest {

	// TODO more testing
	private Account account;

	private Box box;

	private Keg keg;

	private User user1;
	private User user2;
	private User user3;

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

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(anyString())).thenReturn(account);
		mockAuthService(authService);
	}

	@Test
	public void testSimple() {
		RankResource rankResource = new RankResource();

		Response response = rankResource.retrieveRank(null,
				String.valueOf(user1.getId()));

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testNoUser(){
		RankResource rankResource = new RankResource();
		
		Response response = rankResource.retrieveRank(null, "noId");
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		
		response = rankResource.retrieveRank(null, null);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		
	}

}
