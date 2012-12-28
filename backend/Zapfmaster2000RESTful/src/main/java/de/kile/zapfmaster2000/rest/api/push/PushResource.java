package de.kile.zapfmaster2000.rest.api.push;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.challenge.StatusAwareAsynchronousResponse;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("/push")
public class PushResource {

	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrieveNews(
			final @Suspend(PlatformConstants.ASYNC_TIMEOUT) AsynchronousResponse pResponse,
			@QueryParam(value = "token") String pToken) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Zapfmaster2000Core.INSTANCE.getPushService().addNewsRequest(
					pResponse, account, pToken);
		} else {
			pResponse.setResponse(Response.status(Status.FORBIDDEN).build());
		}
	}

	@GET
	@Path("/draftkit/{boxid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrieveBoxUpdate(
			final @Suspend(PlatformConstants.ASYNC_TIMEOUT) AsynchronousResponse pResponse,
			@PathParam("boxid") int pBoxId, @QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			// check if account has box with given id
			Box box = null;
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Box> boxes = session
					.createQuery("FROM Box b WHERE b.account.id = :accountId")
					.setLong("accountId", account.getId()).list();
			for (Box b : boxes) {
				if (b.getId() == pBoxId) {
					box = b;
				}
			}
			tx.commit();

			if (box != null) {
				Zapfmaster2000Core.INSTANCE.getPushService()
						.addDraftkitRequest(pResponse, box, pToken);
			} else {
				// TODO
				// pResponse
				// .setResponse(Response.status(Status.FORBIDDEN).build());
			}
		}
		// TODO
		// pResponse.setResponse(Response.status(Status.FORBIDDEN).build());

	}

	@GET
	@Path("/challenge")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrieveChallengeUpdates(
			final @Suspend(PlatformConstants.ASYNC_TIMEOUT) AsynchronousResponse pResponse,
			@QueryParam("token") String pToken) {

		User user = Zapfmaster2000Core.INSTANCE.getAuthService().retrieveUser(
				pToken);
		if (user != null) {
			StatusAwareAsynchronousResponse statusAwareAsynchronousResponse = new StatusAwareAsynchronousResponse(
					pResponse);
			Zapfmaster2000Core.INSTANCE.getChallengeService().rememberUser(
					user, statusAwareAsynchronousResponse);
			Zapfmaster2000Core.INSTANCE.getPushService().addChallengeRequest(
					statusAwareAsynchronousResponse, user);
		} else {
			// TODO: forbidden
		}
	}

}
