package de.kile.zapfmaster2000.rest.api.news;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;

@Path("news")
public class NewsResource {

	private static final Logger LOG = Logger.getLogger(NewsResource.class);

	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveNews(@QueryParam("start") int pStart,
			@QueryParam("length") int pLength,
			@DefaultValue("") @QueryParam("filter") String filter,
			@QueryParam("token") String pToken) {

		LOG.debug("Retrieving news");

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<News> result;
			if (filter == null || filter.isEmpty()) {
				result = session
						.createQuery(
								"SELECT n FROM News n WHERE n.account.id = :accountId"
										+ " ORDER BY n.date DESC")
						.setLong("accountId", account.getId())
						.setMaxResults(pLength).setFirstResult(pStart).list();
			} else {
				result = session
						.createQuery(
								"SELECT n FROM " + filter
										+ " n WHERE n.account.id = :accountId"
										+ " ORDER BY n.date DESC")
						.setLong("accountId", account.getId())
						.setMaxResults(pLength).setFirstResult(pStart).list();
			}
			tx.commit();

			// adapt news (to ResponseBean)
			List<AbstractNewsResponse> newsResp = new ArrayList<>();
			NewsAdapter adapter = new NewsAdapter();
			for (News news : result) {
				newsResp.add(adapter.adapt(news));
			}

			return Response.ok(newsResp).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

}
