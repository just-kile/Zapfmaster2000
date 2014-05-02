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
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.UserType;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class RankingsBuilderImpl implements RankingsBuilder {

	// TODO add empty user
	/* (non-Javadoc)
	 * @see de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilderI#retrieveUserAmountResponse(java.util.Date, java.util.Date, int, de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserAmountResponse[] retrieveUserAmountResponse(Date dFrom,
			Date dTo, int maxResults, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		account = (Account) session.load(Zapfmaster2000Package.eINSTANCE
				.getAccount().getName(), account.getId());

		if (maxResults == -1) {
			maxResults = Integer.MAX_VALUE;
		}

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setMaxResults(maxResults)
					.list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " AND d.date > :from"
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setMaxResults(maxResults).list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " AND d.date BETWEEN :from AND :to"
									+ " GROUP BY u.id ORDER BY amt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setTimestamp("to", dTo).setMaxResults(maxResults).list();
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

	/* (non-Javadoc)
	 * @see de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilderI#retrieveDrawCountUserListResponse(java.util.Date, java.util.Date, int, de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DrawCountUserListResponse[] retrieveDrawCountUserListResponse(
			Date dFrom, Date dTo, int maxResults, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		account = (Account) session.load(Zapfmaster2000Package.eINSTANCE
				.getAccount().getName(), account.getId());

		if (maxResults == -1) {
			maxResults = Integer.MAX_VALUE;
		}

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " GROUP BY u.id ORDER BY cnt DESC, u.name")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setMaxResults(maxResults)
					.list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " AND d.date > :from"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setMaxResults(maxResults).list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(d.id) AS cnt, u.imagePath"
									+ " FROM User u, Drawing d "
									+ " WHERE d.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " AND d.date BETWEEN :from AND :to"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setMaxResults(maxResults).setTimestamp("to", dTo).list();
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

	/* (non-Javadoc)
	 * @see de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilderI#retrieveAchievementUserListResponse(java.util.Date, java.util.Date, int, de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AchievementUserListResponse[] retrieveAchievementUserListResponse(
			Date dFrom, Date dTo, int maxResults, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		account = (Account) session.load(Zapfmaster2000Package.eINSTANCE
				.getAccount().getName(), account.getId());

		if (maxResults == -1) {
			maxResults = Integer.MAX_VALUE;
		}

		List<Object[]> list;
		if (dFrom == null) {// Full list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account "
									+ " AND u.type != :guest"
									+ " GROUP BY u.id ORDER BY cnt DESC, u.name")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setMaxResults(maxResults)
					.list();

		} else if (dTo == null) {// List until now
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account "
									+ " AND g.date > :from"
									+ " AND u.type != :guest"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setMaxResults(maxResults).list();
		} else { // general list
			list = session
					.createQuery(
							"SELECT u.id, u.name, COUNT(g.id) AS cnt, u.imagePath"
									+ " FROM User u, GainedAchievement g "
									+ " WHERE g.user = u AND u.account = :account "
									+ " AND g.date BETWEEN :from AND :to"
									+ " AND u.type != :guest"
									+ " GROUP BY u.id ORDER BY cnt DESC")
					.setParameter("guest", UserType.GUEST)
					.setEntity("account", account).setTimestamp("from", dFrom)
					.setTimestamp("to", dTo).setMaxResults(maxResults).list();
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
