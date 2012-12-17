package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class DrinkProgressResource {

	private static final Logger LOG = Logger.getLogger(RankingsResource.class);

	private Date dFrom;
	private Date dTo;
	private int interval;

	@SuppressWarnings("unchecked")
	@Path("achievements")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveDrinkProgress(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser, @QueryParam("from") String pFrom,
			@QueryParam("to") String pTo,
			@QueryParam("interval") String pInterval) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			String sQuery = createQuery(pFrom, pTo, pUser);

			Query query = session.createQuery(sQuery);

			if (pUser != null) {
				try {
					Long user = Long.valueOf(pUser);
					query.setLong("user", user);

				} catch (NumberFormatException e) {
					LOG.error("Could not parse user id", e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}
			if (pFrom != null) {
				try {
					dFrom = df.parse(pFrom);
				} catch (ParseException e) {
					LOG.error("Could not parse date", e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			if (pTo != null) {
				try {
					dTo = df.parse(pTo);
				} catch (ParseException e) {
					LOG.error("Could not parse date", e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

			if (dFrom != null) {
				query.setTimestamp("from", dFrom);
			}
			if (dTo != null) {
				query.setTimestamp("to", dTo);
			}

			query.setEntity("account", account);
			List<Object[]> list = query.list();
			tx.commit();

			if (list.size() == 0)
				return Response.status(Status.BAD_REQUEST).build();

			if (pInterval != null) {
				try {
					interval = Integer.parseInt(pInterval);
				} catch (NumberFormatException e) {
					LOG.error("Could not parse interval", e);
					return Response.status(Status.BAD_REQUEST).build();
				}
			} else
				interval = 30;

			double[] amounts = createAmountArray(list);

			DrinkProgressResponse response = new DrinkProgressResponse();
			response.setAmount(amounts);
			response.setFrom(dFrom);
			response.setInterval(interval);

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	private double[] createAmountArray(List<Object[]> list) {
		Date maxDate = (Date) list.get(0)[1];
		Date minDate = (Date) list.get(0)[1];
		for (Object[] rawResponse : list) {
			if (((Date) rawResponse[1]).after(maxDate))
				maxDate = (Date) rawResponse[1];
			if (((Date) rawResponse[1]).before(minDate))
				minDate = (Date) rawResponse[1];
		}

		if (dTo != null)
			maxDate = dTo;
		if (dFrom != null)
			minDate = dFrom;
		else
			dFrom = minDate;

		double[] amount = new double[(int) Math.ceil(Double.valueOf(maxDate
				.getTime() - minDate.getTime())
				/ 1000 / 60 / interval)];

		Calendar cal = Calendar.getInstance();
		cal.setTime(minDate);
		cal.add(Calendar.MINUTE, interval);

		Calendar curDate = Calendar.getInstance();
		int idx = 0;

		for (Object[] rawAmountDate : list) {
			curDate.setTime((Date) rawAmountDate[1]);

			while (cal.before(curDate)) {
				cal.add(Calendar.MINUTE, interval);
				idx++;
			}
			amount[idx] += (Double) rawAmountDate[0];
		}

		return amount;
	}

	private String createQuery(String pFrom, String pTo, String pUser) {
		// TODO change to criteria
		String sQuery = "SELECT d.amount, d.date" + " FROM Drawing d, User u"
				+ " WHERE u.account = :account AND d.user = u"
				+ " %USER% %FROM% %TO% ORDER BY d.date";

		if (pUser != null)
			sQuery = sQuery.replaceAll("%USER%", " AND u.id = :user");
		else
			sQuery = sQuery.replaceAll("%USER%", "");

		if (pFrom != null)
			sQuery = sQuery.replaceAll("%FROM%", " AND d.date > :from");
		else
			sQuery = sQuery.replaceAll("%FROM%", "");

		if (pTo != null)
			sQuery = sQuery.replaceAll("%TO%", " AND d.date < :to");
		else
			sQuery = sQuery.replaceAll("%TO%", "");

		return sQuery;
	}
}
