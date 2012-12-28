package de.kile.zapfmaster2000.rest.api.draftkit;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

@Path("/draftkit")
public class DraftKitResource {

	private static final Logger LOG = Logger.getLogger(DraftKitResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retriveAvailableDraftKits(@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery("From Box b WHERE b.account.id = :accountId")
					.setLong("accountId", account.getId()).list();
			tx.commit();

			List<DraftKitResponse> kits = new ArrayList<>();
			for (Box box : result) {
				DraftKitResponse response = new DraftKitResponse();
				response.setBoxId(box.getId());
				response.setName(box.getLocation());
				kits.add(response);
			}

			return Response.ok(kits).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	@GET
	@Path("/{draftKitId}/rfid")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrieveUnknownRfidTags(
			final @Suspend(PlatformConstants.ASYNC_TIMEOUT) AsynchronousResponse pResponse,
			@QueryParam(value = "token") String pToken,
			@PathParam("draftKitId") long pDraftKitId) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery(
							"From Box b WHERE b.account.id = :accountId AND b.id = :boxId")
					.setLong("accountId", account.getId())
					.setLong("boxId", pDraftKitId).list();
			tx.commit();

			if (result.size() == 1) {
				Box box = result.get(0);
				Zapfmaster2000Core.INSTANCE.getPushService()
						.addUnkownRfidRequest(pResponse, box);
			} else {
				// TODO: error
				LOG.warn("Draftkit " + pDraftKitId
						+ " does not belong to account " + account.getId());
			}

		} else {
			// TODO:
			// pResponse.setResponse(Response.status(Status.FORBIDDEN).build());
			LOG.warn("Forbidden");
		}
	}
}
