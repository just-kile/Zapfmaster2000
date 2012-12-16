package de.kile.zapfmaster2000.rest.api.statistics;

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

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class KegResource {

	
	/**
	 * @param pToken
	 * @return either {@link KegResponse} or <code>null</code> if
	 *         <code>pToken</code> is not valid.
	 */
	@SuppressWarnings("unchecked")
	@Path("kegStats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveKegStats(@QueryParam("token") String pToken) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			List<Object> resultCurrentKeg = session
					.createQuery(
							"SELECT k.id, k.brand, k.size, k.startDate "
									+ " FROM Keg k, Drawing d, User u "
									+ " WHERE d.keg = k AND d.user = u "
									+ " AND u.account = :account"
									+ " ORDER BY d.date DESC")
					.setEntity("account", account).setMaxResults(1).list();

			List<Object> resultNumberKegs = session
					.createQuery(
							"SELECT COUNT (DISTINCT k.id) FROM Keg k, Drawing d, User u"
									+ " WHERE d.keg = k AND d.user = u "
									+ " AND u.account= :account")
					.setEntity("account", account).list();

			List<Object> resultCurrentAmount = session
					.createQuery(
							"SELECT (k.size-SUM(d.amount)) FROM Drawing d, Keg k, User u "
									+ " WHERE d.keg = k AND d.user = u "
									+ " AND u.account = :account"
									+ " GROUP BY (k.id)"
									+ " ORDER BY MAX(d.date) DESC")
					.setEntity("account", account).setMaxResults(1).list();

			tx.commit();

			KegResponse response = new KegResponse();

			Object[] resultRow = (Object[]) resultCurrentKeg.get(0);
			response.setKegId((Long) resultRow[0]);
			response.setBrand((String) resultRow[1]);
			response.setSize((Integer) resultRow[2]);
			response.setStart_date((Date) resultRow[3]);
			response.setKegNumbers((Long) resultNumberKegs.get(0));
			response.setCurrentAmount((Double) resultCurrentAmount.get(0));

			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}
}
