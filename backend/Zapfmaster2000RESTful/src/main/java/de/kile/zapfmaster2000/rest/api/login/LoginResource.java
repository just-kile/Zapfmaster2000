package de.kile.zapfmaster2000.rest.api.login;

import javax.ws.rs.Consumes;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account")
	public Response userLogin(AccountLoginParameter pLoginParam) {
		LOG.debug("Login request for account " + pLoginParam.getAccountName());

		String token = Zapfmaster2000Core.INSTANCE.getAuthService()
				.loginAccount(pLoginParam.getAccountName());
		if (token == null) {
			// log in failed
			return Response.status(Status.FORBIDDEN).build();
		}

		// log in succeeded
		return Response.ok(token).build();
	}
}
