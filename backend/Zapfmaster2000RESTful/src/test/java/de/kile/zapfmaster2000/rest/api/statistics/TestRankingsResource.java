package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link RankingsResource}. Different users, multiple
 * {@link Drawing}, multiple {@link Account}
 * 
 * @author PB
 * 
 */
public class TestRankingsResource extends AbstractMockingTest {

	private Account account;
	private Account account2;
	
	private Box box, box2;
	private Keg keg, keg2;
	
	private User user1, user2, user3, userForeignAcc, userEmpty;
	private Drawing drawing1, drawing2, drawing3, drawing4, drawing5;
	private Achievement achievement1, achievement2, achievement3;

	private Date tooEarly, midDate, midDate2, tooLate;
	private String midDateString, midDate2String;

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
		userEmpty = createUser("Wilfried", "/imagePath/image.jpg", "user-pw",
				401, Sex.MALE, 85, UserType.USER, account);
		
		SimpleDateFormat df = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);
		// try {
		// tooEarly = df.parse("20110101-090000");
		// tooLate = df.parse("20120201-130000");
		// midDate = df.parse("20120101-120000");
		// midDate2 = df.parse("20120101-130000");
		// } catch (ParseException e){
		// //
		// }
		tooEarly = createDate(2011, 1, 1, 12, 0, 0);
		tooLate = createDate(2012, 1, 1, 14, 0, 0);

		midDate = createDate(2012, 1, 1, 11, 0, 0);
		midDate2 = createDate(2012, 1, 1, 12, 0, 0);

		midDateString = df.format(midDate);
		midDate2String = df.format(midDate2);

		box = createBox("123", "home", "0.1", account);
		box2 = createBox("123", "home", "0.1", account2);
		
		keg = createKeg("TestBier", tooEarly, null, 50, box);
		keg2 = createKeg("TestBier", tooEarly, null, 50, box2);		
		
		// test drawings		
		drawing1 = createDrawing(5.14, midDate, keg, user1);
		drawing2 = createDrawing(2.71, midDate2, keg, user2);
		drawing3 = createDrawing(4.1, tooEarly, keg, user3);
		drawing4 = createDrawing(6, tooLate, keg, user3);
		drawing5 = createDrawing(20, midDate, keg2, userForeignAcc);
		

		// Achievements
		achievement3 = createAchievement("achievement-3", "desc3", "img/ach3");
		achievement1 = createAchievement("achievement-1", "desc1", "img/ach1");
		achievement2 = createAchievement("achievement-2", "desc2", "img/ach2");

		createGainedAchievement(createDate(2012, 1, 1), user1, achievement1);
		createGainedAchievement(createDate(2012, 2, 1), user1, achievement2);
		createGainedAchievement(createDate(2012, 3, 1), user2, achievement1);
		createGainedAchievement(createDate(2012, 4, 1), userForeignAcc,
				achievement1);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(null)).thenReturn(account);
		mockAuthService(authService);

	}

	@Test
	public void testAmountRanking() {
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource.bestUserListTimeSpan(null, null,
				null);

		Object[] rawUserAmountResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawUserAmountResponse.length);

		assertEquals(user3.getId(),
				((UserAmountResponse) rawUserAmountResponse[0]).getId());
		assertEquals(user1.getId(),
				((UserAmountResponse) rawUserAmountResponse[1]).getId());

		assertEquals(user1.getImagePath(),
				((UserAmountResponse) rawUserAmountResponse[1]).getImage());
	}

	@Test
	public void testAmountFrom() {
		RankingsResource rankingsResource = new RankingsResource();

		// String pFrom = "20120101-100000";

		Response response = rankingsResource.bestUserListTimeSpan(
				midDateString, null, null);

		Object[] rawUserAmountResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawUserAmountResponse.length);
		assertEquals(user3.getId(),
				((UserAmountResponse) rawUserAmountResponse[0]).getId());
	}

	@Test
	public void testAmountFromTo() {
		RankingsResource rankingsResource = new RankingsResource();
		
		Response response = rankingsResource.bestUserListTimeSpan(
				midDateString, midDate2String, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Object[] rawUserAmountResponse = (Object[]) response.getEntity();

		assertEquals(2, rawUserAmountResponse.length);
		// order should have changed
		assertEquals(user1.getId(),
				((UserAmountResponse) rawUserAmountResponse[0]).getId());
	}

	@Test
	public void testDrawCountRanking() {
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource.drawCountUserListTimeSpan(null,
				null, null);

		Object[] rawDrawCountResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawDrawCountResponse.length);

		assertEquals(user3.getDrawings().size(),
				((DrawCountUserListResponse) rawDrawCountResponse[0])
						.getDrawCount());
		assertEquals(user1.getDrawings().size(),
				((DrawCountUserListResponse) rawDrawCountResponse[1])
						.getDrawCount());
	}

	@Test
	public void testDrawCountFrom() {
		RankingsResource rankingsResource = new RankingsResource();

		String pFrom = "20120101-100000";

		Response response = rankingsResource.drawCountUserListTimeSpan(pFrom,
				null, null);

		Object[] rawDrawCountResponse = (Object[]) response.getEntity();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		assertEquals(3, rawDrawCountResponse.length);
		assertEquals(user1.getDrawings().size(),
				((DrawCountUserListResponse) rawDrawCountResponse[0])
						.getDrawCount());
	}

	@Test
	public void testDrawCountFromTo() {
		RankingsResource rankingsResource = new RankingsResource();

		String pFrom = "20120101-100000";
		String pTo = "20120101-130000";

		Response response = rankingsResource.drawCountUserListTimeSpan(pFrom,
				pTo, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Object[] rawDrawCountResponse = (Object[]) response.getEntity();

		assertEquals(2, rawDrawCountResponse.length);
		assertEquals(user1.getDrawings().size(),
				((DrawCountUserListResponse) rawDrawCountResponse[0])
						.getDrawCount());
	}

}
