package de.kile.zapfmaster2000.rest.api.calibration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

@Path("calibration")
public class CalibrationResource {

	@GET
	@Path("/boxes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllBoxes(String token) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(token);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery("SELECT Box b WHERE b.account.id = :accountId")
					.setLong("account", account.getId()).list();

			List<CalibrationResponse> responses = new ArrayList<>();
			for (Box b : result) {
				CalibrationResponse response = new CalibrationResponse();
				response.setBoxId(b.getId());
				response.setDisturbance(b.getTickDisturbanceTerm());
				response.setRegression(b.getTickRegressor());
				response.setTickReduction(b.getTickReduction());
				responses.add(response);
			}

			tx.commit();
			return Response.ok(responses).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

}
