package de.kile.zapfmaster2000.rest.core.box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationConstants;
import de.kile.zapfmaster2000.rest.impl.core.box.DrawServiceImpl;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestDrawService extends AbstractDatabaseTest {

	private static final long RFID_TAG_1 = 123;

	private static final long RFID_TAG_2 = 234;

	private Box box;

	private Date creationDateKeg2;

	@Before
	public void createEntities() {
		Account account = createAccount("Hogwards");
		box = createBox("box-1", "Gryffindor Tower", "1.0", 0, 0.001, 0,
				account);
		
		creationDateKeg2 = createDate(2012, 10, 18);
		createKeg("Muggel Brew", createDate(2012, 10, 17),
				creationDateKeg2, 50, box);
		createKeg("Muggel Brew", creationDateKeg2, null, 50, box);

		createUser("Harry", "Scar.png", "Seeker", RFID_TAG_1, Sex.MALE, 50,
				UserType.USER, account);
		createUser("Ron", "HarryAndMe.png", "IWantToBeHarry", RFID_TAG_2,
				Sex.MALE, 50, UserType.USER, account);
	}

	@After
	public void waitForAutoLogout() throws InterruptedException {
		int time = Zapfmaster2000Core.INSTANCE.getConfigurationService()
				.getInt(ConfigurationConstants.BOX_LOGIN_AUTO_LOGOUT);
		Thread.sleep((long) (time * 1.1)); // +10% to have time to write stuff
											// to the database
	}

	@Test
	public void testLoginSimple() {
		DrawServiceImpl mgr = retrieveDrawManager();
		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());
	}

	@Test
	public void testLoginTwice() {
		DrawServiceImpl mgr = retrieveDrawManager();

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());
	}

	@Test
	public void testLoginOtherUserAlreadyLoggedIn() {
		DrawServiceImpl mgr = retrieveDrawManager();

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_2);
		assertNull(user); // harry is still logge in
	}

	@Test
	public void testAutoLogout() throws InterruptedException {
		DrawServiceImpl mgr = new DrawServiceImpl(box);

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_2);
		assertNull(user); // harry is still logged in

		// wait for auto log-off
		waitForAutoLogout();

		// ron logs in one more, should succeed now
		user = mgr.login(RFID_TAG_2);
		assertNotNull(user);
		assertEquals("Ron", user.getName());
	}

	@Test
	public void testUserDoesNotExist() {
		DrawServiceImpl mgr = retrieveDrawManager();

		User user = mgr.login(-1);
		assertNull(user);
	}

	@Test
	public void testSimpleDraw() throws InterruptedException {
		DrawServiceImpl mgr = retrieveDrawManager();
		mgr.login(RFID_TAG_1);
		mgr.draw(200);

		waitForAutoLogout();

		// check if drawing was added to database
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Drawing> drawings = session.createQuery("FROM Drawing").list();

		assertEquals(1, drawings.size());
		Drawing drawing = drawings.get(0);

		// amount = a2*ticks^2 + a1*ticks + a0
		// with a2 = a0 = 0 and a1 = 0.001
		assertEquals(0.2, drawing.getAmount(), 1);

		// check if right user was chosen
		assertNotNull(drawing.getUser());
		assertEquals(RFID_TAG_1, drawing.getUser().getRfidTag());

		// check if right keg was chosen
		assertNotNull(drawing.getKeg());
		assertEquals(creationDateKeg2, drawing.getKeg().getStartDate()); // this

		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDrawGuestUser() throws InterruptedException {
		// note: there is no guest user in the database yet
		DrawServiceImpl mgr = retrieveDrawManager();
		mgr.draw(200); // drawing without login -> guest eats it!!
		waitForAutoLogout();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		// check that guest user was created
		List<User> users = session
				.createQuery("FROM User u WHERE u.type=:guest")
				.setParameter("guest", UserType.GUEST).list();
		assertEquals(1, users.size());

		// check if drawing was added to database
		List<Drawing> drawings = session.createQuery(
				"SELECT d FROM Drawing d JOIN d.user").list();

		assertEquals(1, drawings.size());
		Drawing drawing = drawings.get(0);
		assertEquals(users.get(0), drawing.getUser());
		tx.commit();

		// draw once more as guest. no new user should be created now!
		mgr.draw(200); // drawing without login
		waitForAutoLogout();

		session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();

		// check that there is still only one guest user
		users = session.createQuery("FROM User u WHERE u.type=:guest")
				.setParameter("guest", UserType.GUEST).list();
		assertEquals(1, users.size());

		// check if drawing was added to database
		drawings = session.createQuery("SELECT d FROM Drawing d JOIN d.user")
				.list();

		assertEquals(2, drawings.size());
		tx.commit();

	}

	private DrawServiceImpl retrieveDrawManager() {
		return new DrawServiceImpl(box);
	}
}
