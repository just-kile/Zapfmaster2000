package de.kile.zapfmaster2000.rest.api.installation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

@Path("/install")
public class InstallationResource {

	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveStatus() {

		StatusResponse response = new StatusResponse();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Long> result = session
				.createQuery("SELECT COUNT(*) FROM Admin").list();

		if (result.size() == 1 && result.get(0) > 0) {
			response.setStatus("installed");
		} else {
			response.setStatus("new");
		}

		tx.commit();

		return Response.ok(response).build();
	}

}
