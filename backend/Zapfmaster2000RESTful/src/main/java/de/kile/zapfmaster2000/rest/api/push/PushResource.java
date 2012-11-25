package de.kile.zapfmaster2000.rest.api.push;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.DrawingNewsResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("/push")
public class PushResource {

	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	public void getNews(final @Suspend(10000) AsynchronousResponse pResponse) {
		BoxServiceListener listener = new BoxServiceListener() {
			
			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {		
			}
			
			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				// TODO check log in, check if box belongs to account, ...
				
				// TODO: retrieve real data
				DrawingNewsResponse news = new DrawingNewsResponse();
				news.setAmount(0.5);
				news.setBrand("news-brand");
				news.setDate(new Date());
				news.setImage("foo.png");
				news.setKegId(123);
				news.setLocation("foo-lcation");
				news.setUserId(100);
				news.setUserName("foo-user");
				
				Response response = Response.ok(news).build();
				pResponse.setResponse(response);
				
				Zapfmaster2000Core.INSTANCE.getBoxService().removeListener(this);
			}
			
			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {

			}
		};
		Zapfmaster2000Core.INSTANCE.getBoxService().addListener(listener);
		
		Thread thread = new Thread() {
			public void run() {
				
			}
		};
		thread.start();
		
		
	}

}
