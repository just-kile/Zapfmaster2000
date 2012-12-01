package de.kile.zapfmaster2000.rest.api.news;

import java.util.ArrayList;
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

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse.Type;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.DrawingNews;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

@Path("news")
public class NewsResource {

	private static final Logger LOG = Logger.getLogger(NewsResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveNews(@QueryParam("start") int pStart,
			@QueryParam("length") int pLength,
			@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<News> result = session
					.createQuery(
							"SELECT n FROM News n WHERE n.account.id = :accountId"
									+ " ORDER BY n.date DESC")
					.setLong("accountId", account.getId())
					.setMaxResults(pLength).setFirstResult(pStart).list();
			tx.commit();

			// adapt news (to ResponseBean)
			List<AbstractNewsResponse> newsResp = new ArrayList<>();
			for (News news : result) {
				newsResp.add(adapt(news));
			}

			return Response.ok(newsResp.toArray()).build();

		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	private AbstractNewsResponse adapt(News pNews) {
		AbstractNewsResponse newsResponse = null;

		switch (pNews.eClass().getClassifierID()) {
		case Zapfmaster2000Package.DRAWING_NEWS:
			DrawingNews drawingNews = (DrawingNews) pNews;
			DrawingNewsResponse drawingResp = new DrawingNewsResponse();
			Drawing drawing = drawingNews.getDrawing();

			drawingResp.setType(Type.DRAWING);
			drawingResp.setAmount(drawing.getAmount());
			drawingResp.setKegId(drawing.getKeg().getId());
			drawingResp.setBrand(drawing.getKeg().getBrand());
			drawingResp.setLocation(drawing.getKeg().getBox().getLocation());
			drawingResp.setImage(drawing.getUser().getImagePath());
			drawingResp.setUserId(drawing.getUser().getId());
			drawingResp.setUserName(drawing.getUser().getName());

			newsResponse = drawingResp;
			break;
		case Zapfmaster2000Package.ACHIEVEMENT_NEWS:
		case Zapfmaster2000Package.OTHER_NEWS:
		default:
			LOG.error("Unsupported news type: " + pNews.getClass().getName());
		}

		if (newsResponse != null) {
			newsResponse.setDate(pNews.getDate());
		}
		return newsResponse;
	}
}
