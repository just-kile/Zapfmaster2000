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
	public Response loginAccount(@FormParam("accountName") String pAccountName) {
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

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/admin")
	public Response loginAdmin(@FormParam("adminName") String adminName,
			@FormParam("password") String password) {
		LOG.debug("Login request for admin " + adminName);

		String token = Zapfmaster2000Core.INSTANCE.getAuthService().loginAdmin(
				adminName, password);
		if (token == null) {
			// log in failed
			return Response.status(Status.FORBIDDEN).build();
		}

		// log in succeeded
		return Response.ok(token).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/user")
	public Response loginUser(@FormParam("userName") String pUserName,
			@FormParam("password") String pPassword) {
		LOG.debug("Login request for account " + pUserName);

		String token = Zapfmaster2000Core.INSTANCE.getAuthService().loginUser(
				pUserName, pPassword);
		if (token == null) {
			// log in failed
			return Response.status(Status.FORBIDDEN).build();
		}

		// log in succeeded
		return Response.ok(token).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/gcm")
	public Response setupGcm(@FormParam("token") String token,
			@FormParam("gcm") String gcm) {
		LOG.debug("Setup gcm request for token" + token);

		Zapfmaster2000Core.INSTANCE.getAuthService()
				.setupGoogleCloudMessagingToken(token, gcm);

		// log in succeeded
		return Response.ok(token).build();
	}
	
}