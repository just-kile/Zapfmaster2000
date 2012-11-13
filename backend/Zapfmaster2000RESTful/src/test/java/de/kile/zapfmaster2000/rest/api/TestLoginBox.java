package de.kile.zapfmaster2000.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.api.bean.Login;
import de.kile.zapfmaster2000.rest.api.login.Credentials;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.mocks.TransactionManagerMock;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestLoginBox extends AbstractRestTest {

	@Before
	public void installMocks() {
		mockTransactionManager(new TransactionManagerMock());
	}

	@Test
	public void correctCredentials() {
		createAccountAndBox("my-box", "my-secret-passphrase");

		Credentials credentials = new Credentials();
		credentials.setName("my-box");
		credentials.setPassphrase("my-secret-passphrase");

		Login login = new Login();
		HttpServletRequest request = createRequestMock();
		Response response = login.userLogin(credentials, request);
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

		verify(request).getSession();
	}

	@Test
	public void checkInvalidCredentials() {
		createAccountAndBox("my-other-box", "my-other-secret-passphrase");

		Credentials credentials = new Credentials();
		credentials.setName("my-other-box");
		credentials.setPassphrase("wrong phrase");

		Login login = new Login();
		HttpServletRequest request = createRequestMock();
		Response response = login.userLogin(credentials, request);
		assertEquals(response.getStatus(),
				Response.Status.FORBIDDEN.getStatusCode());

		credentials.setName("foo");
		response = login.userLogin(credentials, request);
		assertEquals(response.getStatus(),
				Response.Status.FORBIDDEN.getStatusCode());

		credentials.setPassphrase("my-other-secret-passphrase");
		response = login.userLogin(credentials, request);
		assertEquals(response.getStatus(),
				Response.Status.FORBIDDEN.getStatusCode());

		verify(request, never()).getSession();
	}

	private void createAccountAndBox(String pId, String pPassphrase) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionManager()
				.getSessionFactory().openSession();
		session.beginTransaction();

		Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setId("my-box");
		box.setPassphrase("my-secret-passphrase");
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
