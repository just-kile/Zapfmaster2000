package de.kile.zapfmaster2000.rest.api.box;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

@Path("/box")
public class DrawResource {

	/** logger */
	private static final Logger LOG = Logger.getLogger(DrawResource.class);

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest pLoginRequest) {
		LOG.info("User " + pLoginRequest.getRfidTag() + " logs in at box "
				+ pLoginRequest.getBoxPassphrase());

		User user = null;
		try {
			user = Zapfmaster2000Core.INSTANCE.getBoxService()
					.getDrawService(pLoginRequest.getBoxPassphrase())
					.login(pLoginRequest.getRfidTag());
		} catch (IllegalArgumentException ex) {
			LOG.warn("Box with passphrase " + pLoginRequest.getBoxPassphrase()
					+ " does not exist", ex);
		}

		if (user != null) {
			BoxUserLoginResponse resp = new BoxUserLoginResponse();
			resp.setUserName(user.getName());
			resp.setImagePath(user.getImagePath());
			return Response.ok().entity(resp).build();
		}
		return Response.status(Status.FORBIDDEN).build();
	}

	@POST
	@Path("/draw")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(DrawRequest pDrawRequest) {

		LOG.info("Draw occurrs at box " + pDrawRequest.getBoxPassphrase());

		try {
			Zapfmaster2000Core.INSTANCE.getBoxService()
					.getDrawService(pDrawRequest.getBoxPassphrase())
					.draw(pDrawRequest.getTicks());
		} catch (IllegalArgumentException ex) {
			LOG.warn("Box with passphrase " + pDrawRequest.getBoxPassphrase()
					+ " does not exist", ex);
		}

		return Response.status(Status.OK).build();
	}
}
