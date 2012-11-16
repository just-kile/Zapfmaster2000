package de.kile.zapfmaster2000.rest.api.box;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Path("{passphrase}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(@PathParam("passphrase") String pBoxPasspharse,
			long pRfidId) {

		LOG.info("User " + pRfidId + " logs in at box " + pBoxPasspharse);

		User user = null;
		try {
			user = Zapfmaster2000Core.INSTANCE.getBoxService()
					.getDrawService(pBoxPasspharse).login(pRfidId);
		} catch (IllegalArgumentException ex) {
			LOG.warn("Box with passphrase " + pBoxPasspharse
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
}
