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
import de.kile.zapfmaster2000.rest.impl.core.statistics.AlcoholResponseBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("statistics")
public class RankResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	// TODO add user independent

	/**
	 * Returns {@link RankResponse} for an user with id <code>pUser</code>.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * @param pUser
	 *            Valid {@link User} id.
	 * @return <ul>
	 *         <li>OK: {@link RankResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("rank")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveRank(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		long user = -1;

		try {
			user = Long.valueOf(pUser);
		} catch (NumberFormatException e) {
			LOG.error("Could not parse user", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (account != null) {
			AlcoholLevelResponse response = AlcoholResponseBuilder
					.retrieveAlcoholLevelResponse(user, account);

			return Response.ok(response).build();
		}
		return Response.status(Status.FORBIDDEN).build();
	}

}
