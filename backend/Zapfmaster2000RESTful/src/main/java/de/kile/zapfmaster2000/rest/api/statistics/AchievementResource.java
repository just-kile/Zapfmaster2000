package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class AchievementResource {
	/**
	 * Returns {@link AchievementResponse} either for everyone or specific to
	 * user with id <code>pUser</code>.
	 * 
	 * @param pToken
	 * @param pUser can be <code>null</code>.
	 * @return either {@link AchievementResponse} or <code>null</code> if
	 *         <code>pToken</code> is not valid.
	 */
	@SuppressWarnings("unchecked")
	@Path("achievements")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAchievementStats(
			@QueryParam("token") String pToken, @QueryParam("user") String pUser) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			List<Object> resultAchievementCount;
			List<Object> resultMostActivity;

			if (pUser == null) {
				resultAchievementCount = session
						.createQuery(
								"SELECT COUNT(g.id) FROM GainedAchievement g, User u"
										+ " WHERE g.user = u"
										+ " AND u.account = :account")
						.setEntity("account", account).list();

				resultMostActivity = session
						.createQuery(
								"SELECT HOUR(g.date) FROM GainedAchievement g, User u "
										+ " WHERE g.user = u "
										+ " AND u.account = :account "
										+ " GROUP BY HOUR(g.date)"
										+ " ORDER BY COUNT(g.id) DESC")
						.setEntity("account", account).setMaxResults(1).list();
			} else {
				resultAchievementCount = session
						.createQuery(
								"SELECT COUNT(g.id) "
										+ " FROM GainedAchievement g, User u "
										+ " WHERE u.id = :user AND g.user = u"
										+ " AND u.account = :account")
						.setLong("user", Long.valueOf(pUser))
						.setEntity("account", account).list();

				resultMostActivity = session
						.createQuery(
								"SELECT HOUR(g.date) FROM GainedAchievement g, User u "
										+ " WHERE g.user = u AND u.id = :user"
										+ " AND u.account = :account"
										+ " GROUP BY HOUR(g.date)"
										+ " ORDER BY COUNT(g.id) DESC")
						.setMaxResults(1).setEntity("account", account)
						.setLong("user", Long.valueOf(pUser)).list();
			}
			tx.commit();

			AchievementResponse response = new AchievementResponse();

			response.setCount((Long) resultAchievementCount.get(0));
			response.setMostAchievementHour((Integer) resultMostActivity.get(0));

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}
}
