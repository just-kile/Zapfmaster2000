package de.kile.zapfmaster2000.rest.impl.core.achievement.processor.local;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.impl.core.achievement.processor.AchievementProcessorListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Achievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.GainedAchievement;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestTrinkerDerStunde extends AbstractDatabaseTest {

	private User user1;

	private User user2;

	private Keg keg;

	private Achievement achievement;

	private TrinkerDerStunde trinkerDerStunde;

	private User userGained;

	@Before
	public void setupEntities() {
		Account account = createAccount("MyAccount");
		Box box = createBox("box-1", "loc-1", "1.0", account);
		keg = createKeg("brand", new Date(), null, 10, box);
		user1 = createUser("user-1", "user-img-1", "pw1", 1, Sex.MALE, 80,
				UserType.USER, account);
		user2 = createUser("user-2", "user-img-2", "pw2", 2, Sex.FEMALE, 60,
				UserType.USER, account);
		achievement = createAchievement("Trinker der Stunde", "Trinker",
				"/tds.jpg");

		userGained = null;

		trinkerDerStunde = new TrinkerDerStunde();
		trinkerDerStunde.init(account, achievement);
		trinkerDerStunde.addListener(new AchievementProcessorListener() {
			@Override
			public void onAchievementGained(GainedAchievement pGainedAchievement) {
				userGained = pGainedAchievement.getUser();
			}
		});
	}

	@Test
	public void testGainFirstDraw() {
		Drawing drawing = createDrawing(0.1, new Date(), keg, user1);
		trinkerDerStunde.process(drawing);
		assertNotNull(userGained);
		assertEquals(user1.getId(), userGained.getId());
	}

	@Test
	public void testGainMultipleDrawings() {
		createDrawing(0.1, new Date(), keg, user1);
		createDrawing(0.1, new Date(), keg, user1);
		createDrawing(0.1, new Date(), keg, user1);

		createDrawing(0.2, new Date(), keg, user2);
		Drawing drawing = createDrawing(0.2, new Date(), keg, user2);

		trinkerDerStunde.process(drawing);
		assertNotNull(userGained);
		assertEquals(user2.getId(), userGained.getId());
	}

	@Test
	public void testNoGain() {
		createDrawing(0.1, new Date(), keg, user1);
		createDrawing(0.1, new Date(), keg, user1);
		createDrawing(0.1, new Date(), keg, user1);

		createDrawing(0.1, new Date(), keg, user2);
		Drawing drawing = createDrawing(0.1, new Date(), keg, user2);

		trinkerDerStunde.process(drawing);
		assertNull(userGained);
	}
	
	@Test
	public void testHugeDrawingLongTimeAgo() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -65);
		Date time = cal.getTime();
		
		createDrawing(0.5,  time, keg, user1); // more than an hour ago
		Drawing drawing = createDrawing(0.2, new Date(), keg, user2);

		trinkerDerStunde.process(drawing);
		assertNotNull(userGained);
		assertEquals(user2.getId(), userGained.getId());
	}
	
	
}
