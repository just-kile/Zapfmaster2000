package de.kile.zapfmaster2000.rest.api.members;

import java.util.ArrayList;
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

import de.kile.zapfmaster2000.rest.api.members.MemberResponse.GainedUserAchievement;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/members")
public class MembersResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveMembers(@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			// TODO: 1+n queries (n is num of users). Is that the best way?
			List<?> result = session
					.createQuery(
							"SELECT u.name, u.imagePath, u.id, SUM(d.amount)"
									+ "FROM User u LEFT OUTER JOIN u.drawings d "
									+ "WHERE u.account.id = :accountId "
									+ "GROUP BY u.id ORDER BY u.name")
					.setLong("accountId", account.getId()).list();

			List<MemberResponse> response = new ArrayList<>();
			for (Object rawResult : result) {
				Object[] resultArray = (Object[]) rawResult;

				MemberResponse memberResponse = new MemberResponse();
				memberResponse.setUserId((Long) resultArray[2]);
				memberResponse.setUserName((String) resultArray[0]);
				memberResponse.setImagePath((String) resultArray[1]);
				if (resultArray[3] instanceof Double) {
					memberResponse.setTotalAmount((Double) resultArray[3]);
				} // else the user did not drink at all yet, leave "0.0"

				List<?> gaResult = session
						.createQuery(
								"SELECT ga.achievement.name, ga.achievement.imagePath,"
										+ " ga.achievement.id FROM GainedAchievement ga "
										+ "WHERE ga.user.id = :userId "
										+ "ORDER BY ga.date DESC")
						.setLong("userId", memberResponse.getUserId()).list();

				for (Object rawGaResult : gaResult) {
					Object[] gaResultArray = (Object[]) rawGaResult;

					GainedUserAchievement gainedAchievement = new GainedUserAchievement();
					gainedAchievement.setAchievementId((Long) gaResultArray[2]);
					gainedAchievement
							.setAchievementName((String) gaResultArray[0]);
					gainedAchievement.setImagePath((String) gaResultArray[1]);

					memberResponse.getAchievements().add(gainedAchievement);
				}

				response.add(memberResponse);
			}

			tx.commit();
			return Response.ok(response).build();
		}
		return Response.status(Status.FORBIDDEN).build();

	}
}
