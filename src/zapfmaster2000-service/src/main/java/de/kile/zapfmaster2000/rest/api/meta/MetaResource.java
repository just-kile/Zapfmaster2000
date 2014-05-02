package de.kile.zapfmaster2000.rest.api.meta;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.kile.zapfmaster2000.rest.constants.VersionConstants;

@Path("meta")
public class MetaResource {

	@GET
	@Path("version")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveVersion() {
		VersionResponse versionResponse = new VersionResponse();
		versionResponse.setBuildNumber(VersionConstants.BUILD_NUMBER);
		versionResponse.setBuildTime(VersionConstants.BUILD_TIME);
		versionResponse.setPomVersion(VersionConstants.POM_VERSION);
		return Response.ok(versionResponse).build();
	}
}
