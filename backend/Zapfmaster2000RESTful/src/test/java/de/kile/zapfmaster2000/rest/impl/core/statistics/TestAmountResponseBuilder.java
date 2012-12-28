package de.kile.zapfmaster2000.rest.impl.core.statistics;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.AmountResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestAmountResponseBuilder extends AbstractMockingTest {
	
	private Account account1;
	private Box box1;
	private Keg keg1;
	private Keg keg2;
	private Keg keg3;
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

		userEmpty = createUser("Franz", "img/user3", "user3-pw", 202, Sex.MALE,
				85, UserType.USER, account1);
		
		box1 = createBox("pass", "home", "0.5", account1);

		keg1 = createKeg("Brand1", createDate(2012, 1, 1),
				createDate(2012, 1, 2), 30, box1);

		keg2 = createKeg("Brand2", createDate(2012, 1, 3), null, 30, box1);
		keg3 = createKeg("Brand1", createDate(2012, 1, 2),
				createDate(2012, 1, 3), 30, box1);

		createDrawing(0.7, createDate(2012, 1, 1, 1, 0, 0), keg1, user1);
		createDrawing(0.3, createDate(2012, 1, 1, 1, 30, 0), keg1, user2);
		createDrawing(3.5, createDate(2012, 1, 3, 3, 0, 0), keg3, user1);
		createDrawing(3.0, createDate(2012, 1, 2, 4, 1, 0), keg2, user2);
	}

	@Test
	public void testSimple() {

		AmountResponse amountResponse = AmountResponseBuilder
				.retrieveAmountResponse(-1, account1);
		assertEquals(7.5, amountResponse.getAmountTotal());
		assertEquals(3.5, amountResponse.getGreatestDrawing());
		assertEquals(3, amountResponse.getMostActivityHour());
	}

	@Test
	public void testUserSpecific() {

		AmountResponse amountResponse = AmountResponseBuilder
				.retrieveAmountResponse(user2.getId(), account1);

		assertEquals(3.3, amountResponse.getAmountTotal());
		assertEquals(3.0, amountResponse.getGreatestDrawing());
		assertEquals(4, amountResponse.getMostActivityHour());
	}
	
	@Test
	public void testUserEmpty(){
		AmountResponse amountResponse = AmountResponseBuilder
				.retrieveAmountResponse(userEmpty.getId(), account1);

		assertEquals(0.0, amountResponse.getAmountTotal());
		assertEquals(0.0, amountResponse.getGreatestDrawing());
		assertEquals(-1, amountResponse.getMostActivityHour());
	}
	
	@Test
	public void testUserNonExistent(){
		AmountResponse amountResponse = AmountResponseBuilder
				.retrieveAmountResponse(666, account1);

		assertEquals(0.0, amountResponse.getAmountTotal());
		assertEquals(0.0, amountResponse.getGreatestDrawing());
		assertEquals(-1, amountResponse.getMostActivityHour());
	}
}