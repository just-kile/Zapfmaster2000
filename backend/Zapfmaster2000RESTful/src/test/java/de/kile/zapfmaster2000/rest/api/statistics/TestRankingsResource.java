package de.kile.zapfmaster2000.rest.api.statistics;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link RankingsResource}.
 * Different users, multiple {@link Drawing}, multiple {@link Account}
 * 
 * @author PB
 *
 */
public class TestRankingsResource extends AbstractMockingTest {

	private Account account;
	private Account account2;

	@Before
	public void setupData() {
		account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		account2 = Zapfmaster2000Factory.eINSTANCE.createAccount();

		//test users
		//number2 in ranking
		User user1 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user1.setName("Horst");

		User user2 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user2.setName("Ingrid");
		
		//number1 in ranking
		User user3 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user3.setName("Waldemar");
		
		//user in different account		
		User user4 = Zapfmaster2000Factory.eINSTANCE.createUser();
		user4.setName("Judas");

		//test drawings
		Drawing drawing1 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing1.setUser(user1);
		drawing1.setAmount(5.14);
		
		Drawing drawing2 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing2.setUser(user2);
		drawing2.setAmount(2.71);
		
		Drawing drawing3 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing3.setUser(user3);
		drawing3.setAmount(4.1);
	
		Drawing drawing4 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing4.setUser(user3);
		drawing4.setAmount(6);
		
		//drawing in different account. should not appear in ranking 
		Drawing drawing5 = Zapfmaster2000Factory.eINSTANCE.createDrawing();
		drawing5.setUser(user4);
		drawing5.setAmount(20);
		
		
		account.getUsers().add(user1);
		account.getUsers().add(user2);
		account.getUsers().add(user3);
		account2.getUsers().add(user4);
		
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(account);
		session.save(account2);

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

		assertEquals(3, rawUsers.length);
		UserAmountResponse user1 = (UserAmountResponse) rawUsers[0];
		UserAmountResponse user2 = (UserAmountResponse) rawUsers[1];

		assertEquals("Waldemar", user1.getName());
		assertEquals("Horst", user2.getName());
	}

}
