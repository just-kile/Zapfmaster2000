package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.statistics.AchievementResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.AlcoholResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.AmountResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.DrawCountResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.DrinkProgressResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.KegResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

/**
 * 
 * @author PB
 * 
 */
@Path("statistics")
public class GlobalStatsResource {
	
	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	private RankingsBuilder rankingsBuilder = new RankingsBuilder();

	/**
	 * Returns {@link GlobalStatsResponse} as a compilation of different
	 * statistics.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * 
	 * @param pProgressFrom
	 *            start of time span for the {@link DrinkProgressResponse}. For
	 *            format see {@link PlatformConstants}. <code>null</code>
	 *            results in full list.
	 * @param pProgressTo
	 *            end of time span for the {@link DrinkProgressResponse}. For
	 *            format see {@link PlatformConstants}. <code>null</code>
	 *            results in list until now.
	 * @param pProgressInterval
	 *            interval for the {@link DrinkProgressResponse}.
	 *            <code>null</code> results in 60 minutes.
	 * @return <ul>
	 *         <li>OK: {@link GlobalStatsResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pProgressFrom</code>,
	 *         <code>pProgressTo</code> or <code>pProgressInterval</code> cannot
	 *         be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("globalStats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveGlobalStats(@QueryParam("token") String pToken,
			@QueryParam("progressFrom") String pProgressFrom,
			@QueryParam("progressTo") String pProgressTo,
			@QueryParam("progressInterval") String pProgressInterval) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {

			Date dProgressFrom = null;
			Date dProgressTo = null;

			int interval = 60;

			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			if (pProgressFrom != null) {
				try {
					dProgressFrom = df.parse(pProgressFrom);
				} catch (ParseException e) {
					LOG.error("Could not parse date pFrom " + pProgressFrom, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
			if (pProgressTo != null) {
				try {
					dProgressTo = df.parse(pProgressTo);
				} catch (ParseException e) {
					LOG.error("Could not parse date pTo " + pProgressTo, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			if (pProgressInterval != null) {
				try {
					interval = Integer.parseInt(pProgressInterval);
				} catch (NumberFormatException e) {
					LOG.error("Could not parse integer pProgressInterval "
							+ pProgressInterval, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			KegResponse[] kegResponses = KegResponseBuilder
					.retrieveKegResponse(account);
			AmountResponse amountResponse = AmountResponseBuilder
					.retrieveAmountResponse(-1, account);
			AchievementResponse achievementResponse = AchievementResponseBuilder
					.retrieveAchievementResponse(-1, account);
			DrawCountResponse drawCountResponse = DrawCountResponseBuilder
					.retrieveDrawCountResponse(-1, account);
			DrawCountUserListResponse[] drawCountUserListResponses = rankingsBuilder
					.retrieveDrawCountUserListResponse(null, null, -1, account);
			UserAmountResponse[] bestUserList = rankingsBuilder
					.retrieveUserAmountResponse(null, null, -1, account);

			Calendar lastHour = Calendar.getInstance();
			lastHour.add(Calendar.HOUR, -1);

			UserAmountResponse[] bestUserListHour = rankingsBuilder
					.retrieveUserAmountResponse(lastHour.getTime(), null, -1,
							account);
			AchievementUserListResponse[] achievementUserList = rankingsBuilder
					.retrieveAchievementUserListResponse(null, null, -1,
							account);
			DrinkProgressResponse progress = DrinkProgressResponseBuilder
					.retrieveDrinkResponse(account, -1, dProgressFrom,
							dProgressTo, interval);
			AlcoholLevelResponse promille = AlcoholResponseBuilder
					.retrieveAlcoholLevelResponse(account);

			GlobalStatsResponse globalStatsResponse = new GlobalStatsResponse();

			globalStatsResponse.setAchievements(achievementResponse);
			globalStatsResponse.setAchievementUserList(achievementUserList);
			globalStatsResponse.setAmount(amountResponse);
			globalStatsResponse.setBestUserList(bestUserList);
			globalStatsResponse.setBestUserListHour(bestUserListHour);
			globalStatsResponse.setDrawCount(drawCountResponse);
			globalStatsResponse
					.setDrawCountUserList(drawCountUserListResponses);
			globalStatsResponse.setKeg(kegResponses);
			globalStatsResponse.setProgress(progress);
			globalStatsResponse.setPromille(promille);

			return Response.ok(globalStatsResponse).build();

		}

		return Response.status(Status.FORBIDDEN).build();

	}
}
