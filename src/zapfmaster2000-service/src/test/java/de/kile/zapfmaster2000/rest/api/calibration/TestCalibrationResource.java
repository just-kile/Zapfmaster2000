package de.kile.zapfmaster2000.rest.api.calibration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
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
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestCalibrationResource extends AbstractMockingTest {

	
	private Box box1;
	private Box box2;
	private Box box3;
	
	@Before
	public void setup() {
		Account account = createAccount("acc");
		box1 = createBox("b1", "l1", "v1", 1, 2, 3, account);
		box2 = createBox("b2", "l2", "v2", -1, -2, -3, account);
		box3 = createBox("b3", "l3", "v3", 11.1, -22.2, 33, account);

		AuthService authService = mock(AuthService.class);
		when(authService.retrieveAccount(any(String.class)))
				.thenReturn(account);
		mockAuthService(authService);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveBoxes() {
		CalibrationResource res = new CalibrationResource();
		Response response = res.retrieveAllBoxes(null);
		assertEquals(200, response.getStatus());

		List<CalibrationResponse> entity = (List<CalibrationResponse>) response
				.getEntity();
		assertEquals(3, entity.size());

		assertCalibrationReponse(box1, entity.get(0));
		assertCalibrationReponse(box2, entity.get(1));
		assertCalibrationReponse(box3, entity.get(2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveSingleBox() {
		CalibrationResource res = new CalibrationResource();
		Response response = res.retrieveSingleBox(null, box2.getId());
		assertEquals(200, response.getStatus());

		List<CalibrationResponse> entity = (List<CalibrationResponse>) response
				.getEntity();
		assertEquals(1, entity.size());

		assertCalibrationReponse(box2, entity.get(0));
	}

	@Test
	public void testRetrieveSingleBoxNotFound() {
		CalibrationResource res = new CalibrationResource();
		final long invalidId = box3.getId() + 123;
		Response response = res.retrieveSingleBox(null, invalidId);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdate() {
		CalibrationResource res = new CalibrationResource();
		Response response = res
				.updateCalibrationParameters(30, 40, 50, null, box2.getId());
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		List<CalibrationResponse> entity = (List<CalibrationResponse>) response
				.getEntity();
		assertEquals(1, entity.size());
		
		Box updatedBox = Zapfmaster2000Factory.eINSTANCE.createBox();
		updatedBox.setA2(50);
		updatedBox.setA1(40);
		updatedBox.setA0(30);
		updatedBox.setId(box2.getId());
		
		assertCalibrationReponse(updatedBox, entity.get(0));

		// check that only box 2 changed
		response = res.retrieveAllBoxes(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		entity = (List<CalibrationResponse>) response.getEntity();
		assertEquals(3, entity.size());

		assertCalibrationReponse(box1, entity.get(0));
		assertCalibrationReponse(updatedBox, entity.get(1));
		assertCalibrationReponse(box3, entity.get(2));

	}

	private void assertCalibrationReponse(Box box, CalibrationResponse response) {
		assertEquals(box.getId(), response.getBoxId());
		assertEquals(box.getA2(), response.getA2(), 0.01);
		assertEquals(box.getA1(), response.getA1(), 0.01);
		assertEquals(box.getA0(), response.getA0(), 0.01);
	}
}
