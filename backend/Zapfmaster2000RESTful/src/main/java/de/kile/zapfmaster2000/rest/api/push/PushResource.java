package de.kile.zapfmaster2000.rest.api.push;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/push")
public class PushResource {

	private static final int TIMEOUT = 60000;

	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	public void retrieveNews(
			final @Suspend(TIMEOUT) AsynchronousResponse pResponse,
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
			final @Suspend(TIMEOUT) AsynchronousResponse pResponse,
			@PathParam("boxid") int pBoxId, @QueryParam("token") String pToken) {
		// we don't to anything yet.
		// TODO: do something!
	}

}
