package de.kile.zapfmaster2000.connector.web;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import de.kile.zapfmaster2000.rest.api.box.DrawRequest;
import de.kile.zapfmaster2000.rest.api.box.LoginRequest;

public class WebCommunicator {

	 // TODO: Extract to a configuration file!
	private static final String URL = "http://zapfmaster2000.dyndns.org:9130/zapfmaster2000-restful-1.0.0-SNAPSHOT/rest/";
	private static String BOX_PASSPHRASE = "box-1";
	
	public int performLogin(long tagId) {
		System.out.println("perfoming login");
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setBoxPassphrase(BOX_PASSPHRASE);
		loginRequest.setRfidTag(tagId);

		ClientRequest request = new ClientRequest(URL + "box/login");
		ClientResponse<?> response = null;
		try {
			response = request.body(MediaType.APPLICATION_JSON,
					loginRequest).post();
		} catch (Exception e) {
			// TODO add logger
			e.printStackTrace();
		}
		int status = 0;
		if (response != null) {
			status = response.getStatus();
		}
		System.out.println("Response: " + status);
		return status;
	}
	
	public int sendTicks(int ticks) {
		DrawRequest drawRequest = new DrawRequest();
		drawRequest.setBoxPassphrase(BOX_PASSPHRASE);
		drawRequest.setTicks(ticks);

		ClientRequest request = new ClientRequest(URL + "box/draw");
		ClientResponse<?> response = null;
		try {
			response = request.body(
					MediaType.APPLICATION_JSON, drawRequest).post();
		} catch (Exception e) {
			// TODO add logger
			e.printStackTrace();
		}
		int tickResponse = 0;
		if (response != null)
			tickResponse = response.getStatus();
		System.out.println("Response: " + tickResponse);
		return tickResponse;
	}
}
