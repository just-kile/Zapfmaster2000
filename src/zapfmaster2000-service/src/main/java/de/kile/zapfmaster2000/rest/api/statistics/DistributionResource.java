package de.kile.zapfmaster2000.rest.api.statistics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.statistics.DistributionResponseBuilder;
import de.kile.zapfmaster2000.rest.impl.core.statistics.RankingsBuilderImpl;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public class DistributionResource {

	@Path("statistics/distribution")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveDistribution(@QueryParam("token") String pToken) {

		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			DistributionResponseBuilder builder = new DistributionResponseBuilder(
					new RankingsBuilderImpl());
			DistributionResponse distribution = builder
					.retrieveAmountResponse(account);
			return Response.ok(distribution).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
}
