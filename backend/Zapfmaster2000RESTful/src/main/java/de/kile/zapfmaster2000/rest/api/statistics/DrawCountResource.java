package de.kile.zapfmaster2000.rest.api.statistics;

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

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class DrawCountResource {

	
	/**
	 * Returns {@link DrawCountResponse} either for everyone or specific to
	 * user with id <code>pUser</code>.
	 * @param pToken
	 * @param pUser can be <code>null</code>.
	 * @return  either {@link DrawCountResponse} or <code>null</code> if
	 *         <code>pToken</code> is not valid.
	 */
	@SuppressWarnings("unchecked")
	@Path("drawCount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveDrawCount(@QueryParam("token") String pToken,
			@QueryParam("user") String pUser) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			session.update(account);

			List<Object> countByHourResult;
			if (pUser == null) {
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
						.setEntity("account", account)
						.setLong("user", Long.valueOf(pUser)).list();
			}

			tx.commit();

			long sum = 0;
			for (Object n : countByHourResult) {
				sum += (Long) n;
			}

			DrawCountResponse drawCountResponse = new DrawCountResponse();
			drawCountResponse.setCount(sum);
			drawCountResponse.setAverageOperationsPerHour(Double.valueOf(sum)
					/ countByHourResult.size());

			return Response.ok(drawCountResponse).build();

		}

		return Response.status(Status.FORBIDDEN).build();
	}
}
