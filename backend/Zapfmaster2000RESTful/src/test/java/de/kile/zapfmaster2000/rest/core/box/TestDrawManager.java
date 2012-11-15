package de.kile.zapfmaster2000.rest.core.box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationConstants;
import de.kile.zapfmaster2000.rest.impl.core.box.DrawManagerImpl;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestDrawManager extends AbstractDatabaseTest {

	private static final long RFID_TAG_1 = 123;

	private static final long RFID_TAG_2 = 234;

	@Before
	public void createUsers() {
		User user1 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("Harry");
		user1.setRfidTag(RFID_TAG_1);

		User user2 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user2.setName("Ron");
		user2.setRfidTag(RFID_TAG_2);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionManager()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(user1);
		session.save(user2);
		tx.commit();
	}

	@After
	public void waitForAutoLogout() throws InterruptedException {
		int time = Zapfmaster2000Core.INSTANCE.getConfigurationManager()
				.getInt(ConfigurationConstants.BOX_LOGIN_AUTO_LOGOUT);
		Thread.sleep(time);
	}

	@Test
	public void testLoginSimple() {
		DrawManagerImpl mgr = new DrawManagerImpl(
				Zapfmaster2000Factory.eINSTANCE.createBox());
		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());
	}

	@Test
	public void testLoginTwice() {
		DrawManagerImpl mgr = new DrawManagerImpl(
				Zapfmaster2000Factory.eINSTANCE.createBox());

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());
	}

	@Test
	public void testLoginOtherUserAlreadyLoggedIn() {
		DrawManagerImpl mgr = new DrawManagerImpl(
				Zapfmaster2000Factory.eINSTANCE.createBox());

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_2);
		assertNull(user); // harry is still logge in
	}
	
	@Test
	public void testAutoLogout() throws InterruptedException {
		DrawManagerImpl mgr = new DrawManagerImpl(
				Zapfmaster2000Factory.eINSTANCE.createBox());

		User user = mgr.login(RFID_TAG_1);
		assertNotNull(user);
		assertEquals("Harry", user.getName());

		user = mgr.login(RFID_TAG_2);
		assertNull(user); // harry is still logged in
		
		// wait for auto log-off
		int time = Zapfmaster2000Core.INSTANCE.getConfigurationManager()
				.getInt(ConfigurationConstants.BOX_LOGIN_AUTO_LOGOUT);
		Thread.sleep(time);
		
		// ron logs in one more, should succeed now
		user = mgr.login(RFID_TAG_2);
		assertNotNull(user);
		assertEquals("Ron", user.getName());		
	}
	
	@Test
	public void testUserDoesNotExist() {
		DrawManagerImpl mgr = new DrawManagerImpl(
				Zapfmaster2000Factory.eINSTANCE.createBox());

		User user = mgr.login(-1);
		assertNull(user);
	}
	

}
