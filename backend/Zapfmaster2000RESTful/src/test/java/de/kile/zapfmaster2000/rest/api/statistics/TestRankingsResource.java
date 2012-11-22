package de.kile.zapfmaster2000.rest.api.statistics;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class TestRankingsResource extends AbstractMockingTest {

	private Account account;

	@Before
	public void setupData() {
		account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		User user1 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("Horst");

		User user2 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("Ingrid");

		account.getUsers().add(user1);
		account.getUsers().add(user2);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(account);
		session.save(user1);
		session.save(user2);

		tx.commit();

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(null)).thenReturn(account);
		mockAuthService(authService);

	}

	@Test
	public void testSimple() {
		RankingsResource rankingsResource = new RankingsResource();
		Response response = rankingsResource.rankUsers(null, null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Object[] rawUsers = (Object[]) response.getEntity();

		assertEquals(2, rawUsers.length);
		UserAmountResponse user1 = (UserAmountResponse) rawUsers[0];
		UserAmountResponse user2 = (UserAmountResponse) rawUsers[1];

		assertEquals("Horst", user1.getName());
		assertEquals("Ingrid", user2.getName());
	}

}
