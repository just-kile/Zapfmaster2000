package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.Calendar;

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
import de.kile.zapfmaster2000.rest.impl.core.statistics.KegResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

/**
 * 
 * @author PB
 * 
 */
@Path("statistics")
public class FrontpageStatsResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	/**
	 * Returns {@link FrontpageStatsResponse}, a compilation of some lists.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * 
	 * @param pMax
	 *            maximum number of results for the lists.
	 * @return <ul>
	 *         <li>OK: {@link FrontpageResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pMax</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("frontpageStats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveFrontpageStats(@QueryParam("token") String pToken,
			@QueryParam("max") String pMax) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		int max = -1;
		if (pMax != null) {
			try {
				max = Integer.valueOf(pMax);
			} catch (NumberFormatException e) {
				LOG.error("Could not parse parameter max.", e);
				return Response.status(Status.BAD_REQUEST).build();
			}
		}

		if (account != null) {

			KegResponse[] kegResponses = KegResponseBuilder
					.retrieveKegResponse(account);
			DrawCountUserListResponse[] drawCountUserListResponses = RankingsBuilder
					.retrieveDrawCountUserListResponse(null, null, max, account);
			UserAmountResponse[] bestUserList = RankingsBuilder
					.retrieveUserAmountResponse(null, null, max, account);

			Calendar lastHour = Calendar.getInstance();
			lastHour.add(Calendar.HOUR, -1);

			UserAmountResponse[] bestUserListHour = RankingsBuilder
					.retrieveUserAmountResponse(lastHour.getTime(), null, max,
							account);
			AchievementUserListResponse[] achievementUserList = RankingsBuilder
					.retrieveAchievementUserListResponse(null, null, max,
							account);
			AlcoholLevelResponse promille = AlcoholResponseBuilder
					.retrieveAlcoholLevelResponse(account);

			FrontpageStatsResponse frontpageStatsResponse = new FrontpageStatsResponse();

			frontpageStatsResponse.setAchievementUserList(achievementUserList);
			frontpageStatsResponse.setBestUserList(bestUserList);
			frontpageStatsResponse.setBestUserListHour(bestUserListHour);
			frontpageStatsResponse
					.setDrawCountUserList(drawCountUserListResponses);
			frontpageStatsResponse.setKegs(kegResponses);
			frontpageStatsResponse.setPromille(promille);

			return Response.ok(frontpageStatsResponse).build();

		}

		return Response.status(Status.FORBIDDEN).build();

	}
}
