package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.AmountResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class AmountResponseBuilder {

	/**
	 * Builds {@link AmountResponse} for given user (or all users).
	 * 
	 * @param user
	 *            set -1 for all users.
	 * @param account
	 * @return MostActivityHour is -1 if the user has no achievements.
	 */
	@SuppressWarnings("unchecked")
	public static AmountResponse retrieveAmountResponse(long user, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Object[]> resultAmounts;
		List<Object> resultMostActivity;

		if (user == -1) {
			resultAmounts = session
					.createQuery(
							"SELECT SUM(d.amount), MAX(d.amount) FROM Drawing d, User u"
									+ " WHERE d.user = u "
									+ " AND u.account = :account")
					.setEntity("account", account).list();

			resultMostActivity = session
					.createQuery(
							"SELECT HOUR(d.date) FROM Drawing d, User u "
									+ " WHERE d.user = u"
									+ " AND u.account = :account"
									+ " GROUP BY HOUR(d.date)"
									+ " ORDER BY SUM(d.amount) DESC")
					.setMaxResults(1).setEntity("account", account).list();
		} else {
			resultAmounts = session
					.createQuery(
							"SELECT SUM(d.amount), MAX(d.amount) FROM Drawing d, User u"
									+ " WHERE d.user = u AND u.id = :user"
									+ " AND u.account = :account")
					.setEntity("account", account).setLong("user", user).list();

			resultMostActivity = session
					.createQuery(
							"SELECT HOUR(d.date) FROM Drawing d, User u "
									+ " WHERE d.user = u AND u.id = :user"
									+ " AND u.account = :account"
									+ " GROUP BY HOUR(d.date)"
									+ " ORDER BY SUM(d.amount) DESC")
					.setMaxResults(1).setEntity("account", account)
					.setLong("user", user).list();

		}
		tx.commit();

		AmountResponse response = new AmountResponse();
		if (resultMostActivity.size() > 0) {
			response.setAmountTotal((Double) resultAmounts.get(0)[0]);
			response.setGreatestDrawing((Double) resultAmounts.get(0)[1]);
			response.setAmountMostActivityHour((Integer) resultMostActivity.get(0));
		} else {
			response.setAmountTotal(0.0);
			response.setGreatestDrawing(0.0);
			response.setAmountMostActivityHour(-1);
		}
		return response;
	}
}
