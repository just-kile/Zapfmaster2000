package de.kile.zapfmaster2000.rest.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;

import de.kile.zapfmaster2000.rest.api.bean.Login;
import de.kile.zapfmaster2000.rest.api.login.Credentials;

public class TestLoginBox {

	@Test
	public void test() {
		Login login = new Login();
		Response response = login.userLogin(new Credentials());
		assertEquals(response.getStatus(),
				Response.Status.FORBIDDEN.getStatusCode());
	}

}
