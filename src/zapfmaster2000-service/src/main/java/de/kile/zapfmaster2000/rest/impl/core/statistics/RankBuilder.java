package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.RankResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class RankBuilder {

	/**
	 * Returns a {@link RankResponse} for a given {@link User}.
	 * 
	 * @param user
	 *            valid id of an {@link User}.
	 * @param account
	 * @return last rank in every discipline if the user is not found or empty.
	 */
	@SuppressWarnings("unchecked")
	public static RankResponse retrieveRank(long user, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		account = (Account) session.load(Zapfmaster2000Package.eINSTANCE.getAccount().getName(), account.getId());

		// TODO full hql solution
		List<Object> listDrawCount = session
				.createQuery(
						"SELECT u.id FROM User u, Drawing d"
								+ " WHERE d.user = u "
								+ " AND u.account = :account"
								+ " GROUP BY u.id "
								+ " ORDER BY COUNT(d.id) DESC")
				.setEntity("account", account).list();

		List<Object> listAmount = session
				.createQuery(
						"SELECT u.id FROM User u, Drawing d"
								+ " WHERE d.user = u "
								+ " AND u.account = :account"
								+ " GROUP BY u.id "
								+ " ORDER BY SUM(d.amount) DESC")
				.setEntity("account", account).list();

		List<Object> listAchievementCount = session
				.createQuery(
						"SELECT u.id FROM User u, GainedAchievement g"
								+ " WHERE g.user = u "
								+ " AND u.account = :account"
								+ " GROUP BY u.id "
								+ " ORDER BY COUNT(g.id) DESC")
				.setEntity("account", account).list();

		tx.commit();

		long rankAmount = 1;
		long rankDrawCount = 1;
		long rankAchievements = 1;

		for (Object o : listDrawCount) {
			if ((Long) o == user) {
				break;
			} else {
				rankDrawCount++;
			}
		}

		for (Object o : listAmount) {
			if ((Long) o == user) {
				break;
			} else {
				rankAmount++;
			}
		}

		for (Object o : listAchievementCount) {
			if ((Long) o == user) {
				break;
			} else {
				rankAchievements++;
			}
		}

		RankResponse response = new RankResponse();
		response.setRankAchievements(rankAchievements);
		response.setRankAmount(rankAmount);
		response.setRankDrawCount(rankDrawCount);

		return response;

	}
}
