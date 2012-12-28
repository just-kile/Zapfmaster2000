package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.DrawCountResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class DrawCountResponseBuilder {

	/**
	 * Builds {@link DrawCountResponse} for given {@link User} (or all users).
	 * 
	 * @param user
	 *            set -1 for all users.
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static DrawCountResponse retrieveDrawCountResponse(long user,
			Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.update(account);

		List<Object> countByHourResult;
		if (user == -1) {
			countByHourResult = session
					.createQuery(
							"SELECT COUNT(d.id) FROM Drawing d, User u "
									+ " WHERE d.user = u AND u.account = :account"
									+ " GROUP BY HOUR(d.date)")
					.setEntity("account", account).list();

		} else {
			countByHourResult = session
					.createQuery(
							"SELECT COUNT(d.id) FROM Drawing d, User u "
									+ " WHERE u.id = :user AND d.user = u "
									+ " AND u.account = :account "
									+ " GROUP BY HOUR(d.date)")
					.setEntity("account", account).setLong("user", user).list();
		}

		tx.commit();

		long sum = 0;
		for (Object n : countByHourResult) {
			sum += (Long) n;
		}

		DrawCountResponse drawCountResponse = new DrawCountResponse();
		drawCountResponse.setCount(sum);
		if (countByHourResult.size() > 0) {
			drawCountResponse.setAverageOperationsPerHour(Double.valueOf(sum)
					/ countByHourResult.size());
		} else {
			drawCountResponse.setAverageOperationsPerHour(0);
		}
		return drawCountResponse;
	}

}
