package de.kile.zapfmaster2000.rest.api.draftkit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

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
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

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
		createUser("", "", "", 0, Sex.FEMALE, 0, UserType.USER, account1);

		Admin account1Admin = createAdmin("a1", "pw1", account1);
		Admin account2Admin = createAdmin("a1", "pw1", account2);
		Admin globalAdmin = createAdmin("a2", "pw2", null);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount("tokenUser")).thenReturn(account1);
		when(authService.retrieveAdmin("tokenAdmin1"))
				.thenReturn(account1Admin);
		when(authService.retrieveAdmin("tokenAdmin2"))
				.thenReturn(account2Admin);
		when(authService.retrieveAdmin("tokenGlobalAdmin")).thenReturn(
				globalAdmin);
		when(authService.retrieveAdmin("tokenInvalid")).thenReturn(null);

		mockAuthService(authService);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetriveAvailableDraftKitsAccountAdmin() {
		Response response = new DraftKitResource()
				.retriveAvailableDraftKits("tokenAdmin1");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		List<DraftKitResponse> entities = (List<DraftKitResponse>) response
				.getEntity();

		assertEquals(2, entities.size());

		internalAssertEquals(box1, entities.get(0));
		internalAssertEquals(box2, entities.get(1));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetriveAvailableDraftKitsUser() {
		Response response = new DraftKitResource()
				.retriveAvailableDraftKits("tokenUser");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		List<DraftKitResponse> entities = (List<DraftKitResponse>) response
				.getEntity();

		assertEquals(2, entities.size());

		internalAssertEquals(box1, entities.get(0));
		internalAssertEquals(box2, entities.get(1));
	}

	@Test
	public void testRetriveAvailableDraftKitsInvalidToken() {
		Response response = new DraftKitResource()
				.retriveAvailableDraftKits("tokenInvalid");

		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
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
				account1.getId(), "tokenInvalid");

		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
		assertNull(response.getEntity());
	}

	@Test
	public void testUpdatePropertiesGlobalAdmin() {
		Response response = new DraftKitResource().updateProperties(
				box1.getId(), "nl", "np", "tokenGlobalAdmin");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Box reloadedBox = reloadBox(box1.getId());
		assertEquals("nl", reloadedBox.getLocation());
		assertEquals("np", reloadedBox.getPassphrase());
	}

	@Test
	public void testUpdatePropertiesVaidAccountAdmin() {
		Response response = new DraftKitResource().updateProperties(
				box1.getId(), "nl", "np", "tokenAdmin1");

		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		Box reloadedBox = reloadBox(box1.getId());
		assertEquals("nl", reloadedBox.getLocation());
		assertEquals("np", reloadedBox.getPassphrase());
	}

	@Test
	public void testUpdatePropertiesInvalidAccountAdmin() {
		Response response = new DraftKitResource().updateProperties(
				box1.getId(), "nl", "np", "tokenAdmin2");

		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		// box shouldn't be changed
		Box reloadedBox = reloadBox(box1.getId());
		assertEquals("loc1", reloadedBox.getLocation());
		assertEquals("box1", reloadedBox.getPassphrase());
	}

	@Test
	public void testUpdatePropertiesInvalidToken() {
		Response response = new DraftKitResource().updateProperties(
				box1.getId(), "nl", "np", "invalidToken");

		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());

		// box shouldn't be changed
		Box reloadedBox = reloadBox(box1.getId());
		assertEquals("loc1", reloadedBox.getLocation());
		assertEquals("box1", reloadedBox.getPassphrase());
	}

	private Box reloadBox(long boxId) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Box reloadedBox = (Box) session.load(Zapfmaster2000Package.eINSTANCE
				.getBox().getName(), boxId);
		tx.commit();
		return reloadedBox;
	}

	private void internalAssertEquals(Box box, DraftKitResponse response) {
		assertEquals(box.getId(), response.getBoxId());
		assertEquals(box.getLocation(), response.getName());
		assertEquals(box.getPassphrase(), response.getPassphrase());
	}
}
