package de.kile.zapfmaster2000.rest.api.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.constants.PlatformConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
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

	/**
	 * Returns {@link UserAmountResponse} list ordered by the amount drawn by
	 * each {@link User} in the given time span Users that have no drawings in
	 * the given time span will not occur in the list.
	 * 
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Path("bestUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByAmount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			session.update(account);

			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			List<Object[]> list;
			try {
				if (pFrom == null) {// Full list
					list = session
							.createQuery(
									"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account "
											+ " GROUP BY u.id ORDER BY amt DESC")
							.setEntity("account", account).list();

				} else if (pTo == null) {// List until now
					Date dFrom = df.parse(pFrom);
					list = session
							// createCriteria
							.createQuery(
									"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account AND "
											+ " d.date > :from"
											+ " GROUP BY u.id ORDER BY amt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom).list();
				} else { // general list
					Date dFrom = df.parse(pFrom);
					Date dTo = df.parse(pTo);
					list = session
							.createQuery(
									"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account AND "
											+ " d.date BETWEEN :from AND :to"
											+ " GROUP BY u.id ORDER BY amt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom)
							.setTimestamp("to", dTo).list();
				}
			} catch (ParseException e) {
				LOG.error("Could not parse date", e);
				return Response.status(Status.BAD_REQUEST).build();
			}

			tx.commit();

			List<UserAmountResponse> resp = new ArrayList<>();
			for (Object[] object : list) {
				UserAmountResponse userAmountResponse = new UserAmountResponse();
				userAmountResponse.setName((String) object[1]);
				userAmountResponse.setId((Long) object[0]);
				userAmountResponse.setAmount((Double) object[2]);
				userAmountResponse.setImage((String) object[3]);
				resp.add(userAmountResponse);
			}

			return Response.ok(resp.toArray()).build();
		}

		return Response.status(Status.FORBIDDEN).build();

	}

	/**
	 * Returns {@link DrawCountUserListResponse} list ordered by the number of
	 * drawings by each {@link User} in the given time span. Users that have no
	 * drawings in the given time span will not occur in the list.
	 * 
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Path("drawCountUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByDrawCount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			session.update(account);

			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			List<Object[]> list;
			try {
				if (pFrom == null) {// Full list
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account "
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account).list();

				} else if (pTo == null) {// List until now
					Date dFrom = df.parse(pFrom);
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account AND "
											+ " d.date > :from"
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom).list();
				} else { // general list
					Date dFrom = df.parse(pFrom);
					Date dTo = df.parse(pTo);
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
											+ " FROM User u, Drawing d "
											+ " WHERE d.user = u AND u.account = :account AND "
											+ " d.date BETWEEN :from AND :to"
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom)
							.setTimestamp("to", dTo).list();

				}
			} catch (ParseException e) {
				LOG.error("Could not parse date", e);
				return Response.status(Status.BAD_REQUEST).build();
			}

			tx.commit();

			List<DrawCountUserListResponse> resp = new ArrayList<>();
			for (Object[] object : list) {
				DrawCountUserListResponse drawCountResponse = new DrawCountUserListResponse();
				drawCountResponse.setName((String) object[1]);
				drawCountResponse.setId((Long) object[0]);
				drawCountResponse.setDrawCount((long) object[2]);
				drawCountResponse.setImage((String) object[3]);
				resp.add(drawCountResponse);
			}

			return Response.ok(resp.toArray()).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	/**
	 * Returns {@link AchievementUserListResponse} list ordered by the number of
	 * achievements by each {@link User} in the given time span. Users that
	 * gained no achievements in the given time span will not occur in the list.
	 * 
	 * @param pFrom
	 *            start of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in full list.
	 * @param pTo
	 *            end of time span. For format see {@link PlatformConstants}.
	 *            <code>null</code> results in list until now.
	 * @param pRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Path("achievementUserList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUserRankingByAchievementCount(
			@QueryParam("from") String pFrom, @QueryParam("to") String pTo,
			@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			session.update(account);

			SimpleDateFormat df = new SimpleDateFormat(
					PlatformConstants.DATE_TIME_FORMAT);

			List<Object[]> list;
			try {
				if (pFrom == null) {// Full list
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
											+ " FROM User u, GainedAchievement g "
											+ " WHERE g.user = u AND u.account = :account "
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account).list();

				} else if (pTo == null) {// List until now
					Date dFrom = df.parse(pFrom);
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
											+ " FROM User u, GainedAchievement g "
											+ " WHERE g.user = u AND u.account = :account AND "
											+ " g.date > :from"
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom).list();
				} else { // general list
					Date dFrom = df.parse(pFrom);
					Date dTo = df.parse(pTo);
					list = session
							.createQuery(
									"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
											+ " FROM User u, GainedAchievement g "
											+ " WHERE g.user = u AND u.account = :account AND "
											+ " g.date BETWEEN :from AND :to"
											+ " GROUP BY u.id ORDER BY cnt DESC")
							.setEntity("account", account)
							.setTimestamp("from", dFrom)
							.setTimestamp("to", dTo).list();

				}
			} catch (ParseException e) {
				LOG.error("Could not parse date", e);
				return Response.status(Status.BAD_REQUEST).build();
			}

			tx.commit();

			List<AchievementUserListResponse> resp = new ArrayList<>();
			for (Object[] object : list) {
				AchievementUserListResponse achievementCountResponse = new AchievementUserListResponse();
				achievementCountResponse.setName((String) object[1]);
				achievementCountResponse.setId((Long) object[0]);
				achievementCountResponse.setCount((Long) object[2]);
				achievementCountResponse.setImage((String) object[3]);
				resp.add(achievementCountResponse);
			}

			return Response.ok(resp.toArray()).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

}
