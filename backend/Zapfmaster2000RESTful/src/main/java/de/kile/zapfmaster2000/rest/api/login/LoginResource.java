package de.kile.zapfmaster2000.rest.api.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.kile.zapfmaster2000.rest.constants.HttpSessionConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

@Path("/login")
public class LoginResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/box")
	public Response userLogin(Credentials credentials,
			@Context HttpServletRequest request) {
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
				.build();

		SessionFactory sessionFactory = Zapfmaster2000Core.INSTANCE
				.getTransactionService().getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			Query query = session
					.createQuery("SELECT a FROM Account a, Box b WHERE b.id = :id AND b.passphrase = :passphrase AND b.account = a");
			query.setString("id", credentials.getName()).setString(
					"passphrase", credentials.getPassphrase());
			@SuppressWarnings("unchecked")
			List<Account> results = query.list();

			if (results.isEmpty()) {
				response = Response.status(Status.FORBIDDEN).build();
			} else {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute(HttpSessionConstants.ACCOUNT,
						results.get(0));
				response = Response.ok().build();
			}

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return response;
	}
}
