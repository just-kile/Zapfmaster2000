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

public class TestCalibrationResource extends AbstractMockingTest {

	@Before
	public void setup() {
		Account account = createAccount("acc");
		createBox("b1", "l1", "v1", 1, 2, 3, account);
		createBox("b2", "l2", "v2", -1, -2, -3, account);
		createBox("b3", "l3", "v3", 11.1, -22.2, 33, account);

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

		List<CalibrationResponseOld> entity = (List<CalibrationResponseOld>) response
				.getEntity();
		assertEquals(3, entity.size());

		assertCalibrationReponse(1, 1, 2, 3, entity.get(0));
		assertCalibrationReponse(2, -1, -2, -3, entity.get(1));
		assertCalibrationReponse(3, 11.1, -22.2, 33, entity.get(2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRetrieveSingleBox() {
		CalibrationResource res = new CalibrationResource();
		Response response = res.retrieveSingleBoxOld(null, 2);
		assertEquals(200, response.getStatus());

		List<CalibrationResponseOld> entity = (List<CalibrationResponseOld>) response
				.getEntity();
		assertEquals(1, entity.size());

		assertCalibrationReponse(2, -1, -2, -3, entity.get(0));
	}

	@Test
	public void testRetrieveSingleBoxNotFound() {
		CalibrationResource res = new CalibrationResource();
		Response response = res.retrieveSingleBoxOld(null, 4);
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUpdate() {
		CalibrationResource res = new CalibrationResource();
		Response response = res
				.updateCalibrationParametersOld(30, 40, 50, null, 2);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		List<CalibrationResponseOld> entity = (List<CalibrationResponseOld>) response
				.getEntity();
		assertEquals(1, entity.size());

		assertCalibrationReponse(2, 30, 40, 50, entity.get(0));

		// check that only box 2 changed
		response = res.retrieveAllBoxes(null);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());

		entity = (List<CalibrationResponseOld>) response.getEntity();
		assertEquals(3, entity.size());

		assertCalibrationReponse(1, 1, 2, 3, entity.get(0));
		assertCalibrationReponse(2, 30, 40, 50, entity.get(1));
		assertCalibrationReponse(3, 11.1, -22.2, 33, entity.get(2));

	}

	private void assertCalibrationReponse(int boxId, double regression,
			double disturbance, int tickReduction, CalibrationResponseOld response) {
		assertEquals(boxId, response.getBoxId());
		assertEquals(regression, response.getRegression(), 0.1);
		assertEquals(disturbance, response.getDisturbance(), 0.1);
		assertEquals(tickReduction, response.getTickReduction());
	}
}
