package de.kile.zapfmaster2000.rest.api.login;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import de.kile.zapfmaster2000.rest.api.news.NewsResource;
import de.kile.zapfmaster2000.rest.constants.HttpSessionConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/login")
public class LoginResource {

	private static final Logger LOG = Logger.getLogger(NewsResource.class);

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/account")
	public Response userLogin(@FormParam("account") String pName,
			@Context HttpServletRequest request) {

		// TODO: check if already logged in

		Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
				.build();

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"SELECT a FROM Account a WHERE a.name = :name").setString(
				"name", pName);

		@SuppressWarnings("unchecked")
		List<Account> results = query.list();

		if (results.isEmpty()) {
			response = Response.status(Status.FORBIDDEN).build();
		} else {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute(HttpSessionConstants.ACCOUNT,
					results.get(0));

			try {
				// TODO: extract path
				response = Response.seeOther(new URI("../page.html")).build();
			} catch (URISyntaxException e) {
				LOG.error("Invalid URI", e);
			}
		}

		session.getTransaction().commit();

		return response;
	}
}
