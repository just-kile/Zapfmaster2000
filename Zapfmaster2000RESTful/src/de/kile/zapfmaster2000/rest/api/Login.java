package de.kile.zapfmaster2000.rest.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.kile.zapfmaster2000.rest.api.model.Credentials;

@Path("/login")
public class Login {

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getXML(Credentials pCredentials,
			@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("myFoo", "foobar");
		System.out.println(pCredentials.getName());
		System.out.println(pCredentials.getPassword());
		return Response.ok().build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String checkLogIn(@CookieParam("session") String name,
			@Context HttpServletRequest req) {

		System.out.println(name);
		System.out.println(req);

		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("myFoo"));

		return "oho";
	}
}
