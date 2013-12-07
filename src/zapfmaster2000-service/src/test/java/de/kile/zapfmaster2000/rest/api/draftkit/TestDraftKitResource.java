package de.kile.zapfmaster2000.rest.api.draftkit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

public class TestDraftKitResource extends AbstractMockingTest {

	private Account account1;
	private Account account2;
	private Box box1;
	private Box box2;
	
	@Before
	public void createEntities() {
		account1 = createAccount("account1");
		account2 = createAccount("account2");

		box1 = createBox("box1", "loc1", "v1", account1);
		box2 = createBox("box2", "loc2", "v2", account1);
		createBox("box3", "loc3", "v3", account2);

		Admin admin = createAdmin("a1", "pw1", account1);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin("tokenAdmin1")).thenReturn(admin);
		when(authService.retrieveAdmin("tokenAdmin2")).thenReturn(null);
		mockAuthService(authService);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveAccountBoxesValidAdmin() {
		Response response = new DraftKitResource().retrieveAccountBoxes(
				account1.getId(), "tokenAdmin1");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		List<DraftKitResponse> entities = (List<DraftKitResponse>) response
				.getEntity();

		assertEquals(2, entities.size());

		internalAssertEquals(box1, entities.get(0));
		internalAssertEquals(box2, entities.get(1));
	}
	
	@Test
	public void testRetrieveAccountBoxesInvalidAdmin() {
		Response response = new DraftKitResource().retrieveAccountBoxes(
				account1.getId(), "tokenAdmin2");

		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
		assertNull(response.getEntity());
	}

	private void internalAssertEquals(Box box, DraftKitResponse response) {
		assertEquals(box.getId(), response.getBoxId());
		assertEquals(box.getLocation(), response.getName());
		assertEquals(box.getPassphrase(), response.getPassphrase());
	}
}
