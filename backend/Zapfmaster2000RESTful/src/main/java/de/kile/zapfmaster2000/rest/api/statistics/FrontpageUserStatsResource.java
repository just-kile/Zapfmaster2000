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
import de.kile.zapfmaster2000.rest.impl.core.statistics.AchievementResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.AmountResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.DrawCountResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * 
 * @author PB
 *
 */
@Path("statistics")
public class FrontpageUserStatsResource {
	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	/**
	 * Returns {@link FrontpageUserStatsResponse} as a compilation of different
	 * statistics about a {@link User}.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * 
	 * @param pUser
	 *            user id of {@link User} for all statistics.
	 * @return <ul>
	 *         <li>OK: {@link FrontpageUserStatsResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code> cannot be parsed or is
	 *         <code>null</code></li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("frontpageUserStats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserStats(@QueryParam("user") String pUser,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {

			long user = -1;

			if (pUser == null) {
				LOG.error("pUser must be given in this context.");
				return Response.status(Status.BAD_REQUEST).build();
			}

			try {
				user = Long.parseLong(pUser);
			} catch (NumberFormatException e) {
				LOG.error("Could not parse user id pUser " + pUser, e);
				return Response.status(Status.BAD_REQUEST).build();
			}

			AmountResponse amount = AmountResponseBuilder
					.retrieveAmountResponse(user, account);
			AchievementResponse achievement = AchievementResponseBuilder
					.retrieveAchievementResponse(user, account);
			DrawCountResponse drawCount = DrawCountResponseBuilder
					.retrieveDrawCountResponse(user, account);
			RankResponse rank = RankBuilder.retrieveRank(user, account);

			FrontpageUserStatsResponse response = new FrontpageUserStatsResponse();

			response.setAchievement(achievement);
			response.setAmount(amount);
			response.setDrawCount(drawCount);
			response.setRank(rank);

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();

	}
}