package de.kile.zapfmaster2000.rest.api.achievementStats;

import java.util.Date;
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

import de.kile.zapfmaster2000.rest.api.achievementStats.AchievementStatsResponse.UserThatGained;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/achievementStats")
public class AchievementStatsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAchievementStats(
			@QueryParam("token") String pToken,
			@QueryParam("id") long pAchievementId) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Object[]> result = session
					.createQuery(
							"SELECT a.id, a.name, a.description, a.imagePath, "
									+ "u.id, u.name, u.imagePath, g.date "
									+ "FROM Achievement a "
									+ "LEFT JOIN a.gained g LEFT JOIN g.user u "
									+ "WITH u.account.id = :accountId "
									+ "WHERE a.id = :achievementId "
									+ "ORDER BY a.name ASC, g.date DESC")
					.setLong("accountId", account.getId())
					.setLong("achievementId", pAchievementId).list();

			tx.commit();
			
			if (result.size() > 0) {
				Object[] fristRow = result.get(0);
				
				AchievementStatsResponse response = new AchievementStatsResponse();
				response.setAchievementId((Long) fristRow[0]);
				response.setAchievementName((String) fristRow[1]);
				response.setDescription((String) fristRow[2]);
				response.setAchievementImage((String) fristRow[3]);
				
				// add users
				for (Object[] resultRow : result) {
					if (resultRow[4] != null) {
						// a user gained that achievement
						UserThatGained user = new UserThatGained();
						user.setUserId((Long) resultRow[4]);
						user.setUserName((String) resultRow[5]);
						user.setUserImage((String) resultRow[6]);
						user.loadDate((Date) resultRow[7]);
						response.getUsers().add(user);
					}
				}	
				
				return Response.ok(response).build();
			} else {
				return Response.ok().build();
			}


		}

		return Response.status(Status.FORBIDDEN).build();

	}

}
