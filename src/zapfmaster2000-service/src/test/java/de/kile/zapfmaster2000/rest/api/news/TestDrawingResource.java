package de.kile.zapfmaster2000.rest.api.news;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractMockingTest;
import de.kile.zapfmaster2000.rest.api.drawing.DrawingResource;
import de.kile.zapfmaster2000.rest.api.drawing.DrawingResponse;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Admin;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestDrawingResource extends AbstractMockingTest {

	private Drawing drawing1;
	private Drawing drawing2;
	private Drawing drawing3;
	private Drawing drawing4;
	private Drawing drawing5;
	private Box box1;
	private Box box2;
	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setup() {
		Account acc = createAccount("my acc");
		Admin admin = createAdmin("admin", "foo", acc);
		
		box1 = createBox("b1", "l1", "v.1", acc);
		box2 = createBox("b2", "l2", "v.1", acc);

		Keg k1 = createKeg("", new Date(), null, 50, box1);
		Keg k2 = createKeg("", new Date(), null, 50, box2);

		user1 = createUser("u1", "i1", "p1", 1, Sex.MALE, 1, UserType.USER, acc);
		user2 = createUser("u2", "i2", "p2", 1, Sex.MALE, 1, UserType.USER, acc);
		user3 = createUser("u3", "i3", "p3", 1, Sex.MALE, 1, UserType.USER, acc);

		drawing1 = createDrawing(0.1, createDate(2013, 01, 01, 01, 01, 01), k1,
				user1);
		drawing2 = createDrawing(0.2, createDate(2013, 02, 02, 02, 02, 02), k2,
				user2);
		drawing3 = createDrawing(0.3, createDate(2013, 03, 03, 03, 03, 03), k1,
				user3);
		drawing4 = createDrawing(0.4, createDate(2013, 04, 04, 04, 04, 04), k2,
				user1);
		drawing5 = createDrawing(0.5, createDate(2013, 05, 05, 05, 05, 05), k1,
				user2);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAdmin(any(String.class))).thenReturn(admin);
		mockAuthService(authService);
	}

	@Test
	public void testRetrieveDrawingsAll() {
		DrawingResource drawingResource = new DrawingResource();
		Response response = drawingResource.retrieveDrawings(null, 0, 50);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(5, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(box1.getId(), "l1", drawing1.getId(),
				user1.getId(), "u1", "i1", 0.1, "20130201-010101",
				drawings.get(4));
		assertDrawingsResponse(box2.getId(), "l2", drawing2.getId(),
				user2.getId(), "u2", "i2", 0.2, "20130302-020202",
				drawings.get(3));
		assertDrawingsResponse(box1.getId(), "l1", drawing3.getId(),
				user3.getId(), "u3", "i3", 0.3, "20130403-030303",
				drawings.get(2));
		assertDrawingsResponse(box2.getId(), "l2", drawing4.getId(),
				user1.getId(), "u1", "i1", 0.4, "20130504-040404",
				drawings.get(1));
		assertDrawingsResponse(box1.getId(), "l1", drawing5.getId(),
				user2.getId(), "u2", "i2", 0.5, "20130605-050505",
				drawings.get(0));
	}

	@Test
	public void testRetrieveDrawingsLimits() {
		DrawingResource drawingResource = new DrawingResource();
		Response response = drawingResource.retrieveDrawings(null, 1, 2);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(2, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(box1.getId(), "l1", drawing3.getId(),
				user3.getId(), "u3", "i3", 0.3, "20130403-030303",
				drawings.get(1));
		assertDrawingsResponse(box2.getId(), "l2", drawing4.getId(),
				user1.getId(), "u1", "i1", 0.4, "20130504-040404",
				drawings.get(0));
	}

	@Test
	public void testChangeDrawAmount() {
		DrawingResource drawingResource = new DrawingResource();
		Response response = drawingResource.changeDrawAmount(drawing2.getId(),
				1.35, null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// retrieve all drawings and check that the right change was saved to db
		response = drawingResource.retrieveDrawings(null, 0, 50);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(5, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(box1.getId(), "l1", drawing1.getId(),
				user1.getId(), "u1", "i1", 0.1, "20130201-010101",
				drawings.get(4));
		assertDrawingsResponse(box2.getId(), "l2", drawing2.getId(),
				user2.getId(), "u2", "i2", 1.3, "20130302-020202",
				drawings.get(3));
		assertDrawingsResponse(box1.getId(), "l1", drawing3.getId(),
				user3.getId(), "u3", "i3", 0.3, "20130403-030303",
				drawings.get(2));
		assertDrawingsResponse(box2.getId(), "l2", drawing4.getId(),
				user1.getId(), "u1", "i1", 0.4, "20130504-040404",
				drawings.get(1));
		assertDrawingsResponse(box1.getId(), "l1", drawing5.getId(),
				user2.getId(), "u2", "i2", 0.5, "20130605-050505",
				drawings.get(0));
	}

	@Test
	public void testChangeDrawAmountFobidden() {
		long drawingIdNotExistent = drawing5.getId() + 123;

		DrawingResource drawingResource = new DrawingResource();
		Response response = drawingResource.changeDrawAmount(
				drawingIdNotExistent, 1.35, null);
		assertEquals(Status.FORBIDDEN.getStatusCode(), response.getStatus());
	}

	private void assertDrawingsResponse(long boxId, String boxName,
			long drawId, long userId, String userName, String userImage,
			double amount, String date, DrawingResponse drawingResponse) {

		assertEquals(boxId, drawingResponse.getBoxId());
		assertEquals(boxName, drawingResponse.getBoxName());
		assertEquals(drawId, drawingResponse.getDrawId());
		assertEquals(userId, drawingResponse.getUserId());
		assertEquals(userName, drawingResponse.getUserName());
		assertEquals(userImage, drawingResponse.getUserImage());
		assertEquals(amount, drawingResponse.getAmount(), 0.1);
		assertEquals(date, drawingResponse.getDate());

	}

}
