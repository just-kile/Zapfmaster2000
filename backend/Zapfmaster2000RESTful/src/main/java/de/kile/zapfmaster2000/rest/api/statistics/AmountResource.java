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

public class AmountResource {

	@SuppressWarnings("unchecked")
	@Path("currentKegStats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCurrentKegAmount(@QueryParam("token") String pToken) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			// TODO restrict to Box

			List<Object> resultAmountCurrentKeg = session.createQuery(
					"SELECT SUM(d.amount), MAX(d.date)" +
					" FROM Keg k, Drawing g" +
					" WHERE d.keg = k " +
					" GROUP BY k.id " +
					" ORDER BY MAX(d.date) DESC").setMaxResults(1).list();

			
			tx.commit();

		}

		return Response.status(Status.FORBIDDEN).build();
	}
}
