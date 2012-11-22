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
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

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
			List<User> list =  session.createQuery("FROM User").list(); //session.createQuery("FROM User u WHERE u.account = :account").setEntity("account", account).list();
			tx.commit();
			
			List<UserAmountResponse> resp = new ArrayList<>();
			for (User user : list) {
				UserAmountResponse userAmountResponse = new UserAmountResponse();
				userAmountResponse.setName(user.getName());
				userAmountResponse.setId(user.getId());
				userAmountResponse.setAmount(0);
				resp.add(userAmountResponse);
			}
			
			return Response.ok(resp.toArray()).build();
		}
		
		return null;
	}

}
