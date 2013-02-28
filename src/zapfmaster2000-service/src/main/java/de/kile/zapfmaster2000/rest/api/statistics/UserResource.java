package de.kile.zapfmaster2000.rest.api.statistics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.statistics.UserResponseBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class UserResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	@Path("user")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUser(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser) {
		// TODO test
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (pUser == null) {
			LOG.error("pUser must be given in this context.");
			return Response.status(Status.BAD_REQUEST).build();
		}

		long user = -1;

		try {
			user = Long.valueOf(pUser);
		} catch (NumberFormatException e) {
			LOG.error("Could not parse user", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (account != null) {
			UserResponse response = UserResponseBuilder.retrieveUserResponse(
					user, account);

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

}
