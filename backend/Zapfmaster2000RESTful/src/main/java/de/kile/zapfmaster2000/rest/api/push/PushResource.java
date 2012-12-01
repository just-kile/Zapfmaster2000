package de.kile.zapfmaster2000.rest.api.push;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

@Path("/push")
public class PushResource {
	
	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	public void getNews(final @Suspend(10000) AsynchronousResponse pResponse) {
		Zapfmaster2000Core.INSTANCE.getPushService().addNewsRequest(pResponse);
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public void getLogin(final @Suspend(10000) AsynchronousResponse pResponse) {
		Zapfmaster2000Core.INSTANCE.getPushService().addLoginRequest(pResponse);
	}

}
