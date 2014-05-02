package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilderImpl;
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * 
 * @author PB
 * 
 */
@Path("statistics/rankings")
public class RankingsResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	private final RankingsBuilder rankingsBuilder = new RankingsBuilderImpl();

	/**
	 * Returns {@link UserAmountResponse} list ordered by the amount drawn by
	 * each {@link User} in the given time span Users that have no drawings in
	 * the given time span will not occur in the list.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * 
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pMax
	 *            number of results. <code>null</code> gives all results.
	 * @return <ul>
	 *         <li>OK: {@link UserAmountResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code>, <code>pFrom</code>,
	 *         <code>pTo</code> or <code>pMaxResults</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("bestUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByAmount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@QueryParam("max") String pMaxResults,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {

			Date dFrom = null;
			Date dTo = null;
			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			if (pFrom != null) {
				try {
					dFrom = df.parse(pFrom);
				} catch (ParseException e) {
					LOG.error("Could not parse date pFrom " + pFrom, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
			if (pTo != null) {
				try {
					dTo = df.parse(pTo);
				} catch (ParseException e) {
					LOG.error("Could not parse date pTo " + pTo, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			int maxResults = -1;
			if (pMaxResults != null) {
				try {
					maxResults = Integer.parseInt(pMaxResults);
				} catch (NumberFormatException e) {
					LOG.error("Could not parse integer pMaxResults "
							+ pMaxResults, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			UserAmountResponse[] userAmountResponses = rankingsBuilder
					.retrieveUserAmountResponse(dFrom, dTo, maxResults, account);

			return Response.ok(userAmountResponses).build();

		}

		return Response.status(Status.FORBIDDEN).build();

	}

	/**
	 * Returns {@link DrawCountUserListResponse} list ordered by the number of
	 * drawings by each {@link User} in the given time span. Users that have no
	 * drawings in the given time span will not occur in the list.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * 
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pMax
	 *            number of results. <code>null</code> gives all results.
	 * @return <ul>
	 *         <li>OK: {@link DrawCountUserListResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code>, <code>pFrom</code>,
	 *         <code>pTo</code> or <code>pMaxResults</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("drawCountUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByDrawCount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@QueryParam("max") String pMaxResults,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Date dFrom = null;
			Date dTo = null;
			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			if (pFrom != null) {
				try {
					dFrom = df.parse(pFrom);
				} catch (ParseException e) {
					LOG.error("Could not parse date pFrom " + pFrom, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
			if (pTo != null) {
				try {
					dTo = df.parse(pTo);
				} catch (ParseException e) {
					LOG.error("Could not parse date pTo " + pTo, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			int maxResults = -1;
			if (pMaxResults != null) {
				try {
					maxResults = Integer.parseInt(pMaxResults);
				} catch (NumberFormatException e) {
					LOG.error("Could not parse integer pMaxResults "
							+ pMaxResults, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			DrawCountUserListResponse[] drawCountUserListResponses = rankingsBuilder
					.retrieveDrawCountUserListResponse(dFrom, dTo, maxResults,
							account);

			return Response.ok(drawCountUserListResponses).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	/**
	 * Returns {@link AchievementUserListResponse} list ordered by the number of
	 * achievements by each {@link User} in the given time span. Users that
	 * gained no achievements in the given time span will not occur in the list.
	 * 
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pMax
	 *            number of results. <code>null</code> gives all results.
	 * @return <ul>
	 *         <li>OK: {@link AchievementUserListResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code>, <code>pFrom</code>,
	 *         <code>pTo</code> or <code>pMaxResults</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("achievementUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByAchievementCount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@QueryParam("max") String pMaxResults,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Date dFrom = null;
			Date dTo = null;
			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			if (pFrom != null) {
				try {
					dFrom = df.parse(pFrom);
				} catch (ParseException e) {
					LOG.error("Could not parse date pFrom " + pFrom, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
			if (pTo != null) {
				try {
					dTo = df.parse(pTo);
				} catch (ParseException e) {
					LOG.error("Could not parse date pTo " + pTo, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			int maxResults = -1;
			if (pMaxResults != null) {
				try {
					maxResults = Integer.parseInt(pMaxResults);
				} catch (NumberFormatException e) {
					LOG.error("Could not parse integer pMaxResults "
							+ pMaxResults, e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			AchievementUserListResponse[] achievementUserListResponses = rankingsBuilder
					.retrieveAchievementUserListResponse(dFrom, dTo,
							maxResults, account);

			return Response.ok(achievementUserListResponses).build();

		}

		return Response.status(Status.FORBIDDEN).build();
	}

}
