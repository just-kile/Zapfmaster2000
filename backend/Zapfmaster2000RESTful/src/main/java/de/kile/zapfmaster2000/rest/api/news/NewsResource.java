package de.kile.zapfmaster2000.rest.api.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.constants.HttpSessionConstants;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;

@Path("news")
public class NewsResource {

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response retrieveNews(@QueryParam("start") int pStart,
			@QueryParam("length") int pLength,
			@Context HttpServletRequest pRequest) {

		// check if the user is logged in
		// TODO: extract this somewhere
		if (pRequest.getSession(false) != null
				&& pRequest.getAttribute(HttpSessionConstants.ACCOUNT) instanceof Account) {

			Account account = (Account) pRequest
					.getAttribute(HttpSessionConstants.ACCOUNT);

			Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.build();

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			session.update(account);

			Transaction tx = session.beginTransaction();
			List<News> news = session
					.createQuery("SELCET n FROM News n WHERE n = :account")
					.setEntity("account", account).list();

			tx.commit();

			return Response.ok(news).header("Access-Control-Allow-Origin", "*")
					.build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
}
