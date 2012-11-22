package de.kile.zapfmaster2000.rest.api.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("news")
public class NewsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveNews(@QueryParam("start") int pStart,
			@QueryParam("length") int pLength,
			@Context HttpServletRequest pRequest) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pRequest);
		if (account != null) {
			// TODO: retrieve real data from db...
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
			return Response.ok(newsResp.toArray()).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
}
