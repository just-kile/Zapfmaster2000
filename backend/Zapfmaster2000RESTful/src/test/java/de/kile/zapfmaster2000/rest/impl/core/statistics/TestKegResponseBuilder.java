package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.statistics.KegResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import static junit.framework.Assert.assertEquals;

public class TestKegResponseBuilder extends AbstractMockingTest {
	private Account account1;
	private Box box1;
	private Keg emptyKeg;
	private Keg keg2;
	private Keg keg3;
	private Calendar start;

	@Before
	public void setupData() {

		truncate();

		account1 = createAccount("foo-account");

		User user1 = createUser("Torsten", "img/user1", "user1-pw", 101,
				Sex.MALE, 85, UserType.USER, account1);
		User user2 = createUser("Bettina", "img/user2", "user2-pw", 202,
				Sex.FEMALE, 85, UserType.USER, account1);

		start = Calendar.getInstance();
		start.add(Calendar.HOUR, -24);

		box1 = createBox("pass", "home", "0.5", account1);

		emptyKeg = createKeg("Brand1", start.getTime(), new Date(), 30, box1);

		// current kegs
		keg2 = createKeg("Brand2", start.getTime(), null, 30, box1);
		keg3 = createKeg("Brand1", start.getTime(), null, 50, box1);

		Calendar drawTime = Calendar.getInstance();
		drawTime.add(Calendar.HOUR, -4);

		createDrawing(20, drawTime.getTime(), emptyKeg, user1);

		createDrawing(5, drawTime.getTime(), keg2, user1);
		createDrawing(5, drawTime.getTime(), keg3, user1);

		drawTime.add(Calendar.HOUR, 2);

		createDrawing(1, drawTime.getTime(), keg2, user2);
		createDrawing(5, drawTime.getTime(), keg3, user1);

		drawTime.add(Calendar.HOUR, 1);

		createDrawing(1, drawTime.getTime(), keg2, user1);
		createDrawing(5, drawTime.getTime(), keg3, user2);

	}

	@Test
	public void testSimple() {
		KegResponse[] kegResponse = KegResponseBuilder
				.retrieveKegResponse(account1);

		assertEquals(2, kegResponse.length);
		assertEquals(3, kegResponse[0].getKegNumber());

		assertConforms(keg2, kegResponse[0]);
		assertConforms(keg3, kegResponse[1]);

	}

	public void assertConforms(Keg keg, KegResponse kegResponse) {
		assertEquals(keg.getBrand(), kegResponse.getBrand());
		assertEquals(keg.getId(), kegResponse.getKegId());
		assertEquals(keg.getSize(), kegResponse.getSize());
		assertEquals(keg.getStartDate(), kegResponse.getStartDate());

		// current amount
		double amount = 0;
		for (Drawing d : keg.getDrawings()) {
			amount += d.getAmount();
		}

		assertEquals(keg.getSize() - amount, kegResponse.getCurrentAmount());

		// TODO test lasts until

	}

}
