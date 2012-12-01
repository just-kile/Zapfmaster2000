package de.kile.zapfmaster2000.rest.api.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.api.news.NewsResource;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

@Path("/login")
public class LoginResource {

	private static final Logger LOG = Logger.getLogger(NewsResource.class);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/account")
	public Response userLogin(@FormParam("accountName") String pAccountName) {
		LOG.debug("Login request for account " + pAccountName);

		String token = Zapfmaster2000Core.INSTANCE.getAuthService()
				.loginAccount(pAccountName);
		if (token == null) {
			// log in failed
			return Response.status(Status.FORBIDDEN).build();
		}

		// log in succeeded
		return Response.ok(token).build();
	}
}
