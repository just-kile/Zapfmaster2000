package de.kile.zapfmaster2000.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

@Path("/login")
public class Login {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_PLAIN })
	public Response getXML() {
		System.out.println("foo");
		try {
			Zapfmaster2000Core.INSTANCE.getTransactionManager();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return Response.ok().build();
	}

}
