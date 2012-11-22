package de.kile.zapfmaster2000.rest.api.login;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.api.news.NewsResource;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("/login")
public class LoginResource {

	private static final Logger LOG = Logger.getLogger(NewsResource.class);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/account")
	public Response userLogin(@FormParam("account") String pName,
			@Context HttpServletRequest pRequest) {
		LOG.debug("Login request for account " + pName);

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.loginAccount(pName, pRequest);
		if (account == null) {
			// log in failed
			return Response.status(Status.FORBIDDEN).build();
		}

		// log in succeeded
		try {
			// TODO: extract path to some constant
			return Response.seeOther(new URI("../page.html")).build();
		} catch (URISyntaxException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
