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
import de.kile.zapfmaster2000.rest.impl.core.statistics.AmountResponseBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("statistics")
public class AmountResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	/**
	 * Returns {@link AmountResponse} for either an user with id
	 * <code>pUser</code> or everyone.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * @param pUser
	 *            Valid {@link User} id, can be null.
	 * @return <ul>
	 *         <li>OK: {@link AmountResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("amount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCurrentKegAmount(
			@QueryParam("token") String pToken, @QueryParam("user") String pUser) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		long user = -1;

		if (pUser != null)
			try {
				user = Long.valueOf(pUser);
			} catch (NumberFormatException e) {
				LOG.error("Could not parse user", e);
				return Response.status(Status.BAD_REQUEST).build();
			}

		if (account != null) {
			AmountResponse response = AmountResponseBuilder
					.retrieveAmountResponse(user, account);

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

}
