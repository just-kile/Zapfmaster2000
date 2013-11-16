package de.kile.zapfmaster2000.rest.api.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;

public class TestAdminResource extends AbstractMockingTest {

	@Test
	public void testCreateGlobalAdminValid() {
		Admin admin = createAdmin("admin", "password", null);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(admin);
		mockAuthService(authService);

		Response response = new AdminResource().createGlobalAdmin("newAdmin",
				"newPassword", null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// check that the admin was really created
		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertTrue(exists);
	}

	@Test
	public void testCreateGlobalAdminInvalidToken() {
		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(null);
		mockAuthService(authService);

		Response response = new AdminResource().createGlobalAdmin("newAdmin",
				"newPassword", null);
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());

		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertFalse(exists);
	}

	@Test
	public void testCreateGlobalAdminAccountAdminOnly() {
		Account account = createAccount("account");
		Admin admin = createAdmin("admin", "password", account);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(admin);
		mockAuthService(authService);

		Response response = new AdminResource().createGlobalAdmin("newAdmin",
				"newPassword", null);
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());

		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertFalse(exists);
	}

	@Test
	public void testCreateAdminAccountWithGlobalAdmin() {
		Account account = createAccount("account");
		Admin admin = createAdmin("admin", "password", null);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(admin);
		mockAuthService(authService);

		Response response = new AdminResource().createAccountAdmin(
				account.getId(), "newAdmin", "newPassword", null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// check that the admin was really created
		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertTrue(exists);

		Admin newAdmin = retrieveAdmin("newAdmin", "newPassword");
		assertEquals(account.getId(), newAdmin.getAccount().getId());
	}

	@Test
	public void testCreateAdminAccountWithValidAccountAdmin() {
		Account account = createAccount("account");
		Admin admin = createAdmin("admin", "password", account);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(admin);
		mockAuthService(authService);

		Response response = new AdminResource().createAccountAdmin(
				account.getId(), "newAdmin", "newPassword", null);

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertTrue(exists);

		Admin newAdmin = retrieveAdmin("newAdmin", "newPassword");
		assertEquals(account.getId(), newAdmin.getAccount().getId());
	}

	@Test
	public void testCreateAdminAccountWithInvalidAccountAccount() {
		Account account = createAccount("account");
		Account account2 = createAccount("account2");
		Admin admin = createAdmin("admin", "password", account);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(admin);
		mockAuthService(authService);

		Response response = new AdminResource().createAccountAdmin(
				account2.getId(), "newAdmin", "newPassword", null);

		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());

		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertFalse(exists);
	}

	@Test
	public void testCreateAdminAccountWithInvalidAdmin() {
		Account account = createAccount("account");

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(anyString())).thenReturn(null);
		mockAuthService(authService);

		Response response = new AdminResource().createAccountAdmin(
				account.getId(), "newAdmin", "newPassword", null);

		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());

		boolean exists = checkAdminExists("newAdmin", "newPassword");
		assertFalse(exists);
	}

	private boolean checkAdminExists(String name, String password) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Admin> result = session
				.createQuery(
						"SELECT a FROM Admin a WHERE a.name = :name AND a.password = :password")
				.setString("name", name).setString("password", password).list();
		boolean exists = !result.isEmpty();
		tx.commit();

		return exists;
	}

	private Admin retrieveAdmin(String name, String password) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Admin> result = session
				.createQuery(
						"SELECT a FROM Admin a WHERE a.name = :name AND a.password = :password")
				.setString("name", name).setString("password", password).list();
		tx.commit();

		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

}
