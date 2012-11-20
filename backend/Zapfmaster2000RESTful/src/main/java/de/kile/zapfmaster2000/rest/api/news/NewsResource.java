package de.kile.zapfmaster2000.rest.api.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("news")
public class NewsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveNews(@QueryParam("start") int pStart,
			@QueryParam("length") int pLength,
			@Context HttpServletRequest pRequest) {

		// check if the user is logged in
		// TODO: extract this somewhere
		// if (pRequest.getSession(false) != null
		// && pRequest.getAttribute(HttpSessionConstants.ACCOUNT) instanceof
		// Account) {

		if (pRequest.getSession(false) != null) {
			// Account account = (Account) pRequest
			// .getAttribute(HttpSessionConstants.ACCOUNT);
			//
			// Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
			// .build();
			//
			// Session session = Zapfmaster2000Core.INSTANCE
			// .getTransactionService().getSessionFactory()
			// .getCurrentSession();
			// session.update(account);

			// Transaction tx = session.beginTransaction();
			// List<News> news = session
			// .createQuery("SELECT n FROM News n WHERE n = :account")
			// .setEntity("account", account).list();
			//
			// tx.commit();

			// TODO: extract adaption
			List<AbstractNewsResponse> newsResp = new ArrayList<>();
			// for (News n : news) {
			// TODO: this is just a dummy
			DrawingNewsResponse resp = new DrawingNewsResponse();
			resp.setUserName("foo");
			resp.setBrand("Foo");
			resp.setDate(new Date());
			resp.setAmount(123);
			resp.setImage("asdf.jpg");
			resp.setKegId(2);
			newsResp.add(resp);
			// }

			return Response.ok(newsResp.toArray()).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
}
