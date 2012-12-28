package de.kile.zapfmaster2000.rest.api.draftkit;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

@Path("/draftkit")
public class DraftKitResource {

	public Response retriveAvailableDraftKits(@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);

		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Box> result = session
					.createQuery("From Box b WHERE b.account.id = :accountId")
					.setLong("accountId", account.getId()).list();
			tx.commit();
			
			List<DraftKitResponse> kits = new ArrayList<>();
			for (Box box : result) {
				DraftKitResponse response = new DraftKitResponse();
				response.setBoxId(box.getId());
				response.setName(box.getLocation());
			}
			
			return Response.ok(kits).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
}
