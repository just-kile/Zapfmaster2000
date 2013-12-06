package de.kile.zapfmaster2000.rest.api.calibration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	public Response retrieveAllBoxes(@QueryParam("token") String token) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(token);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery("FROM Box b WHERE b.account.id = :accountId")
					.setLong("accountId", account.getId()).list();

			List<CalibrationResponse> responses = new ArrayList<>();
			for (Box b : result) {
				responses.add(extractCalibrationValues(b));
			}

			tx.commit();
			return Response.ok(responses).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
	
	@GET
	@Path("/boxes/{boxId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveSingleBox(@QueryParam("token") String token,
			@PathParam("boxId") long boxId) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(token);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery(
							"FROM Box b WHERE b.account.id = :accountId AND b.id = :boxId")
					.setLong("accountId", account.getId())
					.setLong("boxId", boxId).list();

			List<CalibrationResponse> responses = new ArrayList<>();
			for (Box b : result) {
				responses.add(extractCalibrationValues(b));
			}

			tx.commit();

			if (responses.size() == 0) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(responses).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	@POST
	@Path("/boxes/{boxId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateCalibrationParameters(@FormParam("a0") double a0,
			@FormParam("a1") double a1, @FormParam("a2") double a2,
			@FormParam("token") String token, @PathParam("boxId") long boxId) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(token);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery(
							"FROM Box b WHERE b.account.id = :accountId AND b.id = :boxId")
					.setLong("accountId", account.getId())
					.setLong("boxId", boxId).list();

			if (result.size() == 0) {
				tx.commit();
				return Response.status(Status.NOT_FOUND).build();
			}

			Box b = result.get(0);
			b.setA0(a0);
			b.setA1(a1);
			b.setA2(a2);
			session.update(b);

			tx.commit();
			return Response.ok(Arrays.asList(extractCalibrationValues(b)))
					.build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
	
	private CalibrationResponse extractCalibrationValues(Box b) {
		CalibrationResponse response = new CalibrationResponse();
		response.setBoxId(b.getId());
		response.setA0(b.getA0());
		response.setA1(b.getA1());
		response.setA2(b.getA2());
		return response;
	}
}
