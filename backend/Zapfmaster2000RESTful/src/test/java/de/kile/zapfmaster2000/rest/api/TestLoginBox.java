package de.kile.zapfmaster2000.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.api.bean.Login;
import de.kile.zapfmaster2000.rest.api.login.Credentials;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestLoginBox extends AbstractDatabaseTest {

	@Test
	public void correctCredentials() {
		createAccountAndBox("my-box", "my-secret-passphrase");

		Credentials credentials = new Credentials();
		credentials.setName("my-box");
		credentials.setPassphrase("my-secret-passphrase");

		Login login = new Login();
		HttpServletRequest request = createRequestMock();
		Response response = login.userLogin(credentials, request);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

		verify(request).getSession();
	}

	@Test
	public void checkInvalidCredentials() {
		createAccountAndBox("my-other-box", "my-other-secret-passphrase");

		Credentials credentials = new Credentials();
		credentials.setName("my-other-box");
		credentials.setPassphrase("wrong-passphrase");

		Login login = new Login();
		HttpServletRequest request = createRequestMock();
		Response response = login.userLogin(credentials, request);
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
				response.getStatus());

		credentials.setName("foo");
		response = login.userLogin(credentials, request);
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
				response.getStatus());

		credentials.setPassphrase("my-other-secret-passphrase");
		response = login.userLogin(credentials, request);
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
				response.getStatus());

		verify(request, never()).getSession();
	}

	private void createAccountAndBox(String pId, String pPassphrase) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionManager()
				.getSessionFactory().openSession();
		session.beginTransaction();

		Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setId(pId);
		box.setPassphrase(pPassphrase);
		account.getBoxes().add(box);

		session.save(account);
		session.save(box);
		session.getTransaction().commit();
		session.close();
	}

	private HttpServletRequest createRequestMock() {
		HttpSession session = mock(HttpSession.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getSession()).thenReturn(session);
		return request;
	}

}
