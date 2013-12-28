package de.kile.zapfmaster2000.rest.core.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Token;

public class TestAuthService extends AbstractDatabaseTest {

	@Before
	public void createEntities() {
		createToken("t1", null, null, null, "gcm1");
		createToken("t2", null, null, null, null);
	}

	@Test
	public void testSetupGcmSuccess() {
		String token = "t2";
		String gcm = "ichbindertollste";
		AuthService authService = Zapfmaster2000Core.INSTANCE.getAuthService();
		authService.setupGoogleCloudMessagingToken(token, gcm);

		// check that the gcm was actually set
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Token> result = session
				.createQuery("SELECT t FROM Token t WHERE t.token = :token")
				.setString("token", token).list();
		tx.commit();

		if (result.size() == 1) {
			Token dbToken = result.get(0);
			assertEquals(gcm, dbToken.getGoogleCloudMessagingToken());
		} else {
			fail("token not found");
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetupGcmTokenDoesNotExist() {
		String token = "t3";
		AuthService authService = Zapfmaster2000Core.INSTANCE.getAuthService();
		authService.setupGoogleCloudMessagingToken(token, "foobar");
	}
	
	@Test
	public void testRetrieveGcmSuccess() {
		AuthService authService = Zapfmaster2000Core.INSTANCE.getAuthService();
		assertEquals("gcm1", authService.retrieveGoogleCloudMessagingToken("t1"));
	}
	
	@Test
	public void testRetrieveGcmNoGcm() {
		AuthService authService = Zapfmaster2000Core.INSTANCE.getAuthService();
		assertEquals(null, authService.retrieveGoogleCloudMessagingToken("t2"));
	}
	
	@Test
	public void testRetrieveGcmInvalidToken() {
		AuthService authService = Zapfmaster2000Core.INSTANCE.getAuthService();
		assertEquals(null, authService.retrieveGoogleCloudMessagingToken("t3"));
	}
}
