package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.AlcoholLevelResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestAlcoholLevelResponseBuilder extends AbstractMockingTest {
	private Account account1;
	private Box box1;
	private Keg keg1;
	private User user1;
	private User user2;
	private User userEmpty;

	@Before
	public void setupData() {

		truncate();

		account1 = createAccount("foo-account");

		user1 = createUser("Torsten", "img/user1", "user1-pw", 101, Sex.MALE,
				85, UserType.USER, account1);
		user2 = createUser("Bettina", "img/user2", "user2-pw", 202, Sex.FEMALE,
				85, UserType.USER, account1);

		userEmpty = createUser("Franz", "img/user3", "user3-pw", 302,
				Sex.FEMALE, 85, UserType.USER, account1);

		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		cal.add(Calendar.MINUTE, -1);

		createDrawing(1, cal.getTime(), keg1, user1);
		createDrawing(0.5, cal.getTime(), keg1, user2);
		cal.add(Calendar.HOUR, -1);
		createDrawing(1, cal.getTime(), keg1, user1);
		createDrawing(0.5, cal.getTime(), keg1, user2);
		cal.add(Calendar.HOUR, -1);
		createDrawing(1, cal.getTime(), keg1, user1);
		createDrawing(0.5, cal.getTime(), keg1, user2);
		cal.add(Calendar.HOUR, -1);
		createDrawing(1, cal.getTime(), keg1, user1);
		createDrawing(0.5, cal.getTime(), keg1, user2);
		cal.add(Calendar.HOUR, -1);
		createDrawing(1, cal.getTime(), keg1, user2);
	}

	@Test
	public void testSimple() {

		AlcoholLevelResponse alcoholLevelResponse = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(user1.getId(), account1);
		assertEquals(2.15, alcoholLevelResponse.getAlcoholLevel(), 0.1);
	}

	@Test
	public void testUserEmpty() {
		AlcoholLevelResponse alcoholLevelResponse = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(userEmpty.getId(), account1);
		assertEquals(0.0, alcoholLevelResponse.getAlcoholLevel());
	}

	@Test
	public void testUserNonExistent() {
		AlcoholLevelResponse alcoholLevelResponse = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(666, account1);
		assertEquals(0.0, alcoholLevelResponse.getAlcoholLevel());
	}

	@Test
	public void testUserIndependent() {
		AlcoholLevelResponse alcoholLevelResponse = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(account1);
		// realistic alcohol level 1-3 per mille

		AlcoholLevelResponse alcoholLevelResponse1 = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(user1.getId(), account1);
		AlcoholLevelResponse alcoholLevelResponse2 = AlcoholResponseBuilder
				.retrieveAlcoholLevelResponse(user2.getId(), account1);

		double avg = (alcoholLevelResponse1.getAlcoholLevel() + alcoholLevelResponse2
				.getAlcoholLevel()) / 2;

		assertEquals(avg, alcoholLevelResponse.getAlcoholLevel(), 0.1);
	}

}
