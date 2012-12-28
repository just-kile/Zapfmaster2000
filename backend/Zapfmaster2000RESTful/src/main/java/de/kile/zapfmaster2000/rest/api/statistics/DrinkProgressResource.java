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
import de.kile.zapfmaster2000.rest.impl.core.statistics.DrinkProgressResponseBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("statistics")
public class DrinkProgressResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	/**
	 * Returns {@link DrinkProgressResponse} for either an user with id
	 * <code>pUser</code> or everyone in a given time span.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * @param pUser
	 *            Valid {@link User} id, can be null.
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pInterval
	 *            interval in minutes, can be <code>null</code> for default
	 *            value 30 minutes.
	 * @return <ul>
	 *         <li>OK: {@link DrinkProgressResponse}</li>
	 *         <li>BAD_REQUEST: if <code>pUser</code>, <code>pFrom</code> or
	 *         <code>pTo</code> cannot be parsed.</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("achievements")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveDrinkProgress(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser, @QueryParam("from") String pFrom,
			@QueryParam("to") String pTo,
			@QueryParam("interval") String pInterval) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		SimpleDateFormat df = new SimpleDateFormat(
				PlatformConstants.DATE_TIME_FORMAT);

		long user = -1;
		Date dFrom = null;
		Date dTo = null;
		int interval = 0;

		if (pUser != null) {
			try {
				user = Long.parseLong(pUser);
			} catch (NumberFormatException e) {
				LOG.error("Could not parse user", e);
				return Response.status(Status.BAD_REQUEST).build();
			}
		}

		if (pFrom != null) {
			try {
				dFrom = df.parse(pFrom);
			} catch (ParseException e) {
				LOG.error("Could not parse date " + pFrom, e);
				return Response.status(Status.BAD_REQUEST).build();
			}
		}

		if (pTo != null) {
			try {
				dTo = df.parse(pTo);
			} catch (ParseException e) {
				LOG.error("Could not parse date " + pTo, e);
				return Response.status(Status.BAD_REQUEST).build();
			}
		}

		if (pInterval != null) {
			try {
				interval = Integer.parseInt(pInterval);
			} catch (NumberFormatException e) {
				LOG.error("Could not parse interval", e);
				return Response.status(Status.BAD_REQUEST).build();
			}
		}

		if (account != null) {

			DrinkProgressResponse response = null;
			if (pInterval != null) {
				response = DrinkProgressResponseBuilder.retrieveDrinkResponse(
						account, user, dFrom, dTo, interval);
			} else {
				response = DrinkProgressResponseBuilder.retrieveDrinkResponse(
						account, user, dFrom, dTo);
			}

			return Response.ok(response).build();

		}

		return Response.status(Status.FORBIDDEN).build();
	}
}
