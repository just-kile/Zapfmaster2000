package de.kile.zapfmaster2000.rest.api.statistics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.impl.core.statistics.KegResponseBuilder;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

@Path("statistics")
public class KegResource {

	/**
	 * Returns {@link KegResponse}.
	 * 
	 * @param pToken
	 *            Token of {@link Account} used for authentication.
	 * @return <ul>
	 *         <li>OK: {@link KegResponse}</li>
	 *         <li>FORBIDDEN: if authentication with <code>pToken</code> fails.</li>
	 *         </ul>
	 */
	@Path("keg")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveKegStats(@QueryParam("token") String pToken) {
		Account account = Zapfmaster2000Core.INSTANCE.getAuthService()
				.retrieveAccount(pToken);
		if (account != null) {
			KegResponse response[] = KegResponseBuilder
					.retrieveKegResponse(account);
			return Response.ok(response).build();
		}

		return Response.status(Status.FORBIDDEN).build();
	}

}
