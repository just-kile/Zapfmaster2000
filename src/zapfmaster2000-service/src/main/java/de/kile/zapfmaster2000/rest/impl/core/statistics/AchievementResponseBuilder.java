package de.kile.zapfmaster2000.rest.impl.core.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.statistics.AchievementListResponse;
import de.kile.zapfmaster2000.rest.api.statistics.AchievementResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class AchievementResponseBuilder {

	/**
	 * Builds {@link AchievementResponse} for given {@link User} (or all users).
	 * 
	 * @param user
	 *            set -1 for all users.
	 * @param account
	 * @return <code>MostActivityHour</code> is -1 if the user has no achievements.
	 */
	@SuppressWarnings("unchecked")
	public static AchievementResponse retrieveAchievementResponse(long user,
			Account account) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Object> resultAchievementCount;
		List<Object> resultMostActivity;
		List<Object> resultGainedAchievementList = new ArrayList<Object>();
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
//			resultGainedAchievementList = session
//					.createQuery("SELECT ga.achievement.name, ga.achievement.imagePath, ga.achievement.description,"
//							+ " ga.achievement.id, ga.date FROM GainedAchievement ga, User u "
//							+ "WHERE ga.user = u "
//							+ "ORDER BY ga.date DESC")
//									.list();
		} else {
			resultAchievementCount = session
					.createQuery(
							"SELECT COUNT(g.id) "
									+ " FROM GainedAchievement g, User u "
									+ " WHERE u.id = :user AND g.user = u"
									+ " AND u.account = :account")
					.setLong("user", user).setEntity("account", account).list();

			resultMostActivity = session
					.createQuery(
							"SELECT HOUR(g.date) FROM GainedAchievement g, User u "
									+ " WHERE g.user = u AND u.id = :user"
									+ " AND u.account = :account"
									+ " GROUP BY HOUR(g.date)"
									+ " ORDER BY COUNT(g.id) DESC")
					.setMaxResults(1).setEntity("account", account)
					.setLong("user", user).list();

		 	

			resultGainedAchievementList = session
					.createQuery("SELECT ga.achievement.name, ga.achievement.imagePath, ga.achievement.description,"
							+ " ga.achievement.id, ga.date FROM GainedAchievement ga "
							+ "WHERE ga.user.id = :userId "
							+ "ORDER BY ga.date DESC")
									.setLong("userId", user)
									.list();
		}
		tx.commit();

		AchievementResponse response = new AchievementResponse();
		
		//set count
		response.setCount((Long) resultAchievementCount.get(0));
		//set mostActivityHour
		if (resultMostActivity.size() > 0) {
			response.setMostAchievementHour((Integer) resultMostActivity.get(0));
		} else {
			response.setMostAchievementHour(-1);
		}
		//set gainedAchievement list
		List<AchievementListResponse> achievementList = new ArrayList<AchievementListResponse>();
		for(int i = 0;i<resultGainedAchievementList.size();i++){
			Object[] resultRow = (Object[]) resultGainedAchievementList.get(i);
			AchievementListResponse achievement = new AchievementListResponse();

			achievement.setAchievementName((String)resultRow[0]);
			achievement.setAchievementImage((String)resultRow[1]);
			achievement.setAchievementDescription((String)resultRow[2]);
			achievement.setAchievementId((long) resultRow[3]);
			achievement.setDate((Date) resultRow[4]);
			
			
			achievementList.add(achievement);
		}
		response.setAchievements(achievementList);
		

		return response;
	}
}
