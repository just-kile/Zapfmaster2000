package de.kile.zapfmaster2000.rest.api.achievements;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/achievements")
public class AchievementsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAchievements(@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Object[]> result = session.createQuery(
					"SELECT a.id, a.name, a.descripiton, a.imagePath FROM a")
					.list();

			tx.commit();

			List<AchievementResonse> response = new ArrayList<>();
			for (Object[] resultRow : result) {
				AchievementResonse resp = new AchievementResonse();
				resp.setAchivementId((Long) resultRow[0]);
				resp.setAchievementName((String) resultRow[1]);
				resp.setAchievementDescription((String) resultRow[2]);
				resp.setAchievementImage((String) resultRow[3]);

				response.add(resp);
			}

			return Response.ok(response).build();

		}

		return Response.status(Status.FORBIDDEN).build();

	}

}
