package de.kile.zapfmaster2000.rest.api.statistics;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

/**
 * Ranks users by amount.
 * Gives out list of {@link UserAmountResponse}.
 * 
 * @author PB
 *
 */
@Path("statistics/rankings")
public class RankingsResource {

	public Response rankUsers(@QueryParam("from") String pFrom,
			@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			session.update(account);

			@SuppressWarnings("unchecked")
			List<Object[]> list = session
					.createQuery(
							"SELECT u.id, u.name, SUM(d.amount) AS amt FROM User u, Drawing d WHERE d.user = u AND u.account = :account GROUP BY u.id ORDER BY amt DESC")
					.setEntity("account", account).list();
			tx.commit();

			List<UserAmountResponse> resp = new ArrayList<>();
			for (Object[] object : list) {
				UserAmountResponse userAmountResponse = new UserAmountResponse();
				userAmountResponse.setName((String) object[1]);
				userAmountResponse.setId((Long) object[0]);
				userAmountResponse.setAmount((Double) object[2]);
				resp.add(userAmountResponse);
			}

			return Response.ok(resp.toArray()).build();
		}

		return null;
	}

}
