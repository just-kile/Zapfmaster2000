package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.AchievementResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class AchievementResponseBuilder {
	
	
	@SuppressWarnings("unchecked")
	public static AchievementResponse buildAchievementResponse(long user,
			Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Object> resultAchievementCount;
		List<Object> resultMostActivity;

		if (user == -1) {
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
					.setLong("user", user)
					.setEntity("account", account).list();

			resultMostActivity = session
					.createQuery(
							"SELECT HOUR(g.date) FROM GainedAchievement g, User u "
									+ " WHERE g.user = u AND u.id = :user"
									+ " AND u.account = :account"
									+ " GROUP BY HOUR(g.date)"
									+ " ORDER BY COUNT(g.id) DESC")
					.setMaxResults(1).setEntity("account", account)
					.setLong("user", user).list();
		}
		tx.commit();

		AchievementResponse response = new AchievementResponse();

		response.setCount((Long) resultAchievementCount.get(0));
		response.setMostAchievementHour((Integer) resultMostActivity.get(0));

		return response;
	}
}
