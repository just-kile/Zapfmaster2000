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
public class AmountResource {

	//TODO handle user parse error
	/**
	 * Returns {@link AmountResponse} either for everyone or specific to user
	 * with id <code>pUser</code>.
	 * 
	 * @param pToken
	 * @param pUser
	 *            can be <code>null</code>.
	 * @return either {@link AmountResponse} or <code>null</code> if
	 *         <code>pToken</code> is not valid.
	 */
	@Path("amount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCurrentKegAmount(
			@QueryParam("token") String pToken, @QueryParam("user") String pUser) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			AmountResponse response = createAmountResponse(pUser, account);
			
			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

	@SuppressWarnings("unchecked")
	public AmountResponse createAmountResponse(String pUser, Account account) {
		Session session = Zapfmaster2000Core.INSTANCE
				.getTransactionService().getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();

		List<Object[]> resultAmounts;
		List<Object> resultMostActivity;

		if (pUser == null) {
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
					.setEntity("account", account)
					.setLong("user", Long.valueOf(pUser)).list();

			resultMostActivity = session
					.createQuery(
							"SELECT HOUR(d.date) FROM Drawing d, User u "
									+ " WHERE d.user = u AND u.id = :user"
									+ " AND u.account = :account"
									+ " GROUP BY HOUR(d.date)"
									+ " ORDER BY SUM(d.amount) DESC")
					.setMaxResults(1).setEntity("account", account)
					.setLong("user", Long.valueOf(pUser)).list();

		}
		tx.commit();

		AmountResponse response = new AmountResponse();
		response.setAmountTotal((Double) resultAmounts.get(0)[0]);
		response.setGreatestDrawing((Double) resultAmounts.get(0)[1]);
		response.setMostActivityHour((Integer) resultMostActivity.get(0));

		return response;
	}
}
