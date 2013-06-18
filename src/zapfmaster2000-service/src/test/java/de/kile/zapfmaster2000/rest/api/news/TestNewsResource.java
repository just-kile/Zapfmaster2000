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
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;

public class TestNewsResource extends AbstractMockingTest {

	@Before
	public void setup() {
		Account acc = createAccount("my acc");
		Box box1 = createBox("b1", "l1", "v.1", acc);
		Box box2 = createBox("b2", "l2", "v.1", acc);

		Keg k1 = createKeg("", new Date(), null, 50, box1);
		Keg k2 = createKeg("", new Date(), null, 50, box2);

		User u1 = createUser("u1", "i1", "p1", 1, Sex.MALE, 1, UserType.USER,
				acc);
		User u2 = createUser("u2", "i2", "p2", 1, Sex.MALE, 1, UserType.USER,
				acc);
		User u3 = createUser("u3", "i3", "p3", 1, Sex.MALE, 1, UserType.USER,
				acc);

		createDrawing(0.1, createDate(2013, 01, 01, 01, 01, 01), k1, u1);
		createDrawing(0.2, createDate(2013, 02, 02, 02, 02, 02), k2, u2);
		createDrawing(0.3, createDate(2013, 03, 03, 03, 03, 03), k1, u3);
		createDrawing(0.4, createDate(2013, 04, 04, 04, 04, 04), k2, u1);
		createDrawing(0.5, createDate(2013, 05, 05, 05, 05, 05), k1, u2);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(any(String.class))).thenReturn(acc);
		mockAuthService(authService);
	}

	@Test
	public void testRetrieveDrawingsAll() {
		NewsResource newsResource = new NewsResource();
		Response response = newsResource.retrieveDrawings(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(5, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(1, "l1", 1, 1, "u1", "i1", 0.1,
				"20130201-010101", drawings.get(4));
		assertDrawingsResponse(2, "l2", 2, 2, "u2", "i2", 0.2,
				"20130302-020202", drawings.get(3));
		assertDrawingsResponse(1, "l1", 3, 3, "u3", "i3", 0.3,
				"20130403-030303", drawings.get(2));
		assertDrawingsResponse(2, "l2", 4, 1, "u1", "i1", 0.4,
				"20130504-040404", drawings.get(1));
		assertDrawingsResponse(1, "l1", 5, 2, "u2", "i2", 0.5,
				"20130605-050505", drawings.get(0));
	}

	@Test
	public void testRetrieveDrawingsLimits() {
		NewsResource newsResource = new NewsResource();
		Response response = newsResource.retrieveDrawings(null, 1, 2);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(2, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(1, "l1", 3, 3, "u3", "i3", 0.3,
				"20130403-030303", drawings.get(1));
		assertDrawingsResponse(2, "l2", 4, 1, "u1", "i1", 0.4,
				"20130504-040404", drawings.get(0));
	}

	@Test
	public void testChangeDrawAmount() {
		NewsResource newsResource = new NewsResource();
		Response response = newsResource.changeDrawAmount(2, 1.35, null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		// retrieve all drawings and check that the right change was saved to db
		response = newsResource.retrieveDrawings(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		@SuppressWarnings("unchecked")
		List<DrawingResponse> drawings = (List<DrawingResponse>) response
				.getEntity();

		assertEquals(5, drawings.size());

		// order: desc by date (note: month stats to count at 0!)
		assertDrawingsResponse(1, "l1", 1, 1, "u1", "i1", 0.1,
				"20130201-010101", drawings.get(4));
		assertDrawingsResponse(2, "l2", 2, 2, "u2", "i2", 1.3,
				"20130302-020202", drawings.get(3));
		assertDrawingsResponse(1, "l1", 3, 3, "u3", "i3", 0.3,
				"20130403-030303", drawings.get(2));
		assertDrawingsResponse(2, "l2", 4, 1, "u1", "i1", 0.4,
				"20130504-040404", drawings.get(1));
		assertDrawingsResponse(1, "l1", 5, 2, "u2", "i2", 0.5,
				"20130605-050505", drawings.get(0));
	}
	
	@Test
	public void testChangeDrawAmountFobidden() {
		// drawing id = 6 does not exist -> forbidden
		NewsResource newsResource = new NewsResource();
		Response response = newsResource.changeDrawAmount(6, 1.35, null);
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
