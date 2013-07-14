package de.kile.zapfmaster2000.rest.api.drawing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.drawing.CalibrationRequest.CalibrationValues;
import de.kile.zapfmaster2000.rest.api.drawing.CalibrationResponse.CalibratedData;
import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.PolynomialRegression;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Ticks;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

@Path("/drawings")
public class DrawingResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveDrawings(@QueryParam("token") String pToken,
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("50") @QueryParam("length") int length) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Drawing> result = session
					.createQuery(
							"FROM Drawing d"
									// + " JOIN FETCH d.user JOIN FETCH d.keg "
									// + " JOIN FETCH d.keg.box"
									+ " WHERE d.user.account.id = :accountId"
									+ " ORDER BY d.date DESC")
					.setLong("accountId", account.getId())
					.setMaxResults(length).setFirstResult(start).list();

			List<DrawingResponse> response = new ArrayList<>();

			SimpleDateFormat format = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			for (Drawing drawing : result) {
				DrawingResponse r = new DrawingResponse();
				r.setAmount(drawing.getAmount());
				r.setBoxId(drawing.getKeg().getBox().getId());
				r.setBoxName(drawing.getKeg().getBox().getLocation());
				r.setDate(format.format(drawing.getDate()));
				r.setDrawId(drawing.getId());
				r.setUserId(drawing.getUser().getId());
				r.setUserImage(drawing.getUser().getImagePath());
				r.setUserName(drawing.getUser().getName());
				response.add(r);
			}

			tx.commit();
			return Response.ok(response).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	@POST
	@Path("/{drawId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response changeDrawAmount(@PathParam("drawId") long drawId,
			@FormParam("amount") double amount,
			@FormParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			EClass drawingClass = Zapfmaster2000Package.eINSTANCE.getDrawing();
			Drawing drawing = (Drawing) session.get(drawingClass.getName(),
					drawId);

			if (drawing == null
					|| drawing.getUser().getAccount().getId() != account
							.getId()) {
				tx.commit();
				return Response.status(Status.FORBIDDEN).build();
			}

			drawing.setAmount(amount);
			session.update(drawing);

			tx.commit();

			return Response.ok(true).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	@GET
	@Path("/{drawId}/ticks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeDrawAmount(@PathParam("drawId") long drawId,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			EClass drawingClass = Zapfmaster2000Package.eINSTANCE.getDrawing();
			Drawing drawing = (Drawing) session.get(drawingClass.getName(),
					drawId);

			if (drawing == null
					|| drawing.getUser().getAccount().getId() != account
							.getId()) {
				tx.commit();
				return Response.status(Status.FORBIDDEN).build();
			}

			List<TicksResponse> response = new ArrayList<>();
			for (Ticks ticks : drawing.getTicks()) {
				TicksResponse ticksResponse = new TicksResponse();
				ticksResponse.setDate(ticks.getDate());
				ticksResponse.setTicks(ticks.getTicks());
				response.add(ticksResponse);
			}

			tx.commit();
			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	@POST
	@Path("/calibration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calibrate(CalibrationRequest calibrationRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(calibrationRequest.getToken());

		if (account != null) {

			List<Double> xValues = new ArrayList<>();
			List<Double> yValues = new ArrayList<>();

			CalibrationResponse calibrationResponse = new CalibrationResponse();
			
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			EClass drawingClass = Zapfmaster2000Package.eINSTANCE.getDrawing();

			for (CalibrationValues value : calibrationRequest.getData()) {
				Drawing drawing = (Drawing) session.get(drawingClass.getName(),
						value.getDrawingId());

				if (drawing == null
						|| drawing.getUser().getAccount().getId() != account
								.getId()) {
					tx.commit();
					return Response.status(Status.FORBIDDEN).build();
				}

				double sum = 0;
				EList<Ticks> allTicks = drawing.getTicks();
				for (Ticks ticks : allTicks) {
					sum += ticks.getTicks();
				}
				double avg = sum / allTicks.size();

				double amountPerInterval = value.getMeasuredAmount() / allTicks.size();
				xValues.add(avg);
				yValues.add(amountPerInterval);

				CalibratedData data = new CalibratedData();
				data.setTicks(avg);
				data.setAmount(amountPerInterval);
				calibrationResponse.getData().add(data);
				
			}

			tx.commit();

			PolynomialRegression polynomialRegression = new PolynomialRegression(
					unbox(xValues), unbox(yValues), 2);
			

			calibrationResponse.setA0(polynomialRegression.beta(0));
			calibrationResponse.setA1(polynomialRegression.beta(1));
			calibrationResponse.setA2(polynomialRegression.beta(2));

			return Response.ok(calibrationResponse).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	private double[] unbox(List<Double> list) {
		double[] result = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

}
