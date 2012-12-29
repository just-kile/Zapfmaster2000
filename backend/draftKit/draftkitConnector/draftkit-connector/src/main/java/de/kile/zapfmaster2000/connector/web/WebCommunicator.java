package de.kile.zapfmaster2000.connector.web;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import de.kile.zapfmaster2000.rest.api.box.DrawRequest;
import de.kile.zapfmaster2000.rest.api.box.LoginRequest;

public class WebCommunicator {

	private static final String URL = "http://localhost:8080/Zapfmaster2000RESTful/rest/";
	private String boxPassphrase = "box-1";
	
	public int performLogin(long tagId) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setBoxPassphrase(boxPassphrase);
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
		drawRequest.setBoxPassphrase(boxPassphrase);
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
