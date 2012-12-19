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

@Path("statistics")
public class AlcoholLevelResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	// TODO add user independent

	/**
	 * Returns the {@link AlcoholLevelResponse} calculated by the Widmark
	 * formula with alcohol break down of 0.15 per mille per hour.
	 * 
	 * @param pToken
	 * @param pUser
	 * @return either {@link AlcoholLevelResponse} or <code>null</code> if
	 *         <code>pUser</code> does not exist or has not drawn anything.
	 */
	@Path("alcoholLevel")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAlcoholLevel(@QueryParam("token") String pToken,
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
					.buildAlcoholLevelResponse(user, account);

			if (response != null)
				return Response.ok(response).build();
			else {
				LOG.error("No drawings found");
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Status.FORBIDDEN).build();
	}

}
