package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.AchievementUserListResponse;
import de.kile.zapfmaster2000.rest.api.statistics.DrawCountUserListResponse;
import de.kile.zapfmaster2000.rest.api.statistics.UserAmountResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class RankingsBuilder {

	// TODO add empty user
	/**
	 * Returns an array of {@link UserAmountResponse} over a time period. Might
	 * be empty.
	 * 
	 * @param dFrom
	 *            can be <code>null</code> for all available drawings
	 * @param dTo
	 *            can be <code>null</code> for until now
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static UserAmountResponse[] retrieveUserAmountResponse(Date dFrom,
			Date dTo, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.update(account);

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setEntity("account", account).list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account AND "
									+ " d.date > :from"
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account AND "
									+ " d.date BETWEEN :from AND :to"
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setTimestamp("to", dTo).list();
		}

		tx.commit();

		List<UserAmountResponse> resp = new ArrayList<UserAmountResponse>();
		for (Object[] object : list) {
			UserAmountResponse userAmountResponse = new UserAmountResponse();
			userAmountResponse.setName((String) object[1]);
			userAmountResponse.setId((Long) object[0]);
			userAmountResponse.setAmount((Double) object[2]);
			userAmountResponse.setImage((String) object[3]);
			resp.add(userAmountResponse);
		}

		return resp.toArray(new UserAmountResponse[resp.size()]);

	}

	/**
	 * Returns an array of {@link DrawCountUserListResponse} over a time period.
	 * Might be empty.
	 * 
	 * @param dFrom
	 *            optional (default: full list)
	 * @param dTo
	 *            optional (default: until now)
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static DrawCountUserListResponse[] retrieveDrawCountUserListResponse(
			Date dFrom, Date dTo, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.update(account);

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account AND "
									+ " d.date > :from"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account AND "
									+ " d.date BETWEEN :from AND :to"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setTimestamp("to", dTo).list();
		}

		tx.commit();

		List<DrawCountUserListResponse> resp = new ArrayList<DrawCountUserListResponse>();
		for (Object[] object : list) {
			DrawCountUserListResponse drawCountResponse = new DrawCountUserListResponse();
			drawCountResponse.setName((String) object[1]);
			drawCountResponse.setId((Long) object[0]);
			drawCountResponse.setDrawCount((long) object[2]);
			drawCountResponse.setImage((String) object[3]);
			resp.add(drawCountResponse);
		}

		return resp.toArray(new DrawCountUserListResponse[resp.size()]);
	}

	/**
	 * Returns an array of {@link AchievementUserListResponse} over a time
	 * period. Might be empty.
	 * 
	 * @param dFrom
	 *            optional (default: full list)
	 * @param dTo
	 *            optional (default: until now)
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static AchievementUserListResponse[] retrieveAchievementUserListResponse(
			Date dFrom, Date dTo, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.update(account);

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account "
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account AND "
									+ " g.date > :from"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account AND "
									+ " g.date BETWEEN :from AND :to"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setTimestamp("to", dTo).list();
		}

		tx.commit();

		List<AchievementUserListResponse> resp = new ArrayList<AchievementUserListResponse>();
		for (Object[] object : list) {
			AchievementUserListResponse achievementCountResponse = new AchievementUserListResponse();
			achievementCountResponse.setName((String) object[1]);
			achievementCountResponse.setId((Long) object[0]);
			achievementCountResponse.setCount((Long) object[2]);
			achievementCountResponse.setImage((String) object[3]);
			resp.add(achievementCountResponse);
		}

		return resp.toArray(new AchievementUserListResponse[resp.size()]);
	}
}
