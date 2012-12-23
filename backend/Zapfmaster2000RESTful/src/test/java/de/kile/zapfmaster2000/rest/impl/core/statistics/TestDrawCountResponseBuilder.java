package de.kile.zapfmaster2000.rest.impl.core.statistics;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.DrawCountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestDrawCountResponseBuilder extends AbstractMockingTest {

	private Account account1;
	private Box box1;
	private Keg keg1;
	private User user2;
	private User userEmpty;

	@Before
	public void setupData() {

		truncate();

		account1 = createAccount("foo-account");

		User user1 = createUser("Torsten", "img/user1", "user1-pw", 101,
				Sex.MALE, 85, UserType.USER, account1);
		user2 = createUser("Bettina", "img/user2", "user2-pw", 202, Sex.FEMALE,
				85, UserType.USER, account1);

		userEmpty = createUser("Franz", "img/user3", "user3-pw", 302, Sex.MALE,
				85, UserType.USER, account1);

		
		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		createDrawing(1, createDate(2012, 1, 1, 1, 0, 0), keg1, user1);
		createDrawing(2, createDate(2012, 2, 1, 2, 0, 0), keg1, user2);
		createDrawing(2, createDate(2012, 3, 1, 2, 0, 0), keg1, user2);
	}

	@Test
	public void testSimple() {
		DrawCountResponse drawCountResponse = DrawCountResponseBuilder
				.retrieveDrawCountResponse(-1, account1);

		assertEquals(3, drawCountResponse.getCount());
		assertEquals(1.5, drawCountResponse.getAverageOperationsPerHour());
	}

	@Test
	public void testUserSpecific() {
		DrawCountResponse drawCountResponse = DrawCountResponseBuilder
				.retrieveDrawCountResponse(user2.getId(), account1);

		assertEquals(2, drawCountResponse.getCount());
		assertEquals(2.0, drawCountResponse.getAverageOperationsPerHour());
	}
	
	@Test
	public void testUserEmpty(){
		DrawCountResponse drawCountResponse = DrawCountResponseBuilder
				.retrieveDrawCountResponse(userEmpty.getId(), account1);

		assertEquals(0, drawCountResponse.getCount());
		assertEquals(0.0, drawCountResponse.getAverageOperationsPerHour());
	}
	
	@Test
	public void TestUserNonExistent(){
		DrawCountResponse drawCountResponse = DrawCountResponseBuilder
				.retrieveDrawCountResponse(666, account1);

		assertEquals(0, drawCountResponse.getCount());
		assertEquals(0.0, drawCountResponse.getAverageOperationsPerHour());

	}

}
