package de.kile.zapfmaster2000.rest.api.login;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;

public class TestLoginResource extends AbstractDatabaseTest {

	// TODO: fix
//	@Test
//	public void correctCredentials() {
//		createAccountAndBox("my-box", "my-secret-passphrase");
//
//		Credentials credentials = new Credentials();
//		credentials.setName("my-box");
//		credentials.setPassphrase("my-secret-passphrase");
//
//		LoginResource login = new LoginResource();
//		HttpServletRequest request = createRequestMock();
//		Response response = login.userLogin(credentials, request);
//		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//		verify(request).getSession();
//	}

//	@Test
//	public void checkInvalidCredentials() {
//		createAccountAndBox("my-other-box", "my-other-secret-passphrase");
//
//		Credentials credentials = new Credentials();
//		credentials.setName("my-other-box");
//		credentials.setPassphrase("wrong-passphrase");
//
//		LoginResource login = new LoginResource();
//		HttpServletRequest request = createRequestMock();
//		Response response = login.userLogin(credentials, request);
//		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
//				response.getStatus());
//
//		credentials.setName("foo");
//		response = login.userLogin(credentials, request);
//		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
//				response.getStatus());
//
//		credentials.setPassphrase("my-other-secret-passphrase");
//		response = login.userLogin(credentials, request);
//		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),
//				response.getStatus());
//
//		verify(request, never()).getSession();
//	}
//
//	private void createAccountAndBox(String pId, String pPassphrase) {
//		Session session = Zapfmaster2000Core.INSTANCE.getTransactionManager()
//				.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		Account account = Zapfmaster2000Factory.eINSTANCE.createAccount();
//		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
//		box.setId(pId);
//		box.setPassphrase(pPassphrase);
//		account.getBoxes().add(box);
//
//		session.save(account);
//		session.save(box);
//		session.getTransaction().commit();
//		session.close();
//	}

//	private HttpServletRequest createRequestMock() {
//		HttpSession session = mock(HttpSession.class);
//		HttpServletRequest request = mock(HttpServletRequest.class);
//		when(request.getSession()).thenReturn(session);
//		return request;
//	}

}
