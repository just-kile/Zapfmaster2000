package de.kile.zapfmaster2000.rest.core.challenge;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.AsynchronousResponse;

/**
 * The status aware asynchronous response wraps a asynchronous response
 * (delegate) but will remember is a response was already set for the delegate.
 * 
 * @author Thomas Kipar
 */
public class StatusAwareAsynchronousResponse implements AsynchronousResponse {

	private final AsynchronousResponse delegate;

	private boolean wasAnswered = false;
	
	/**
	 * Creates the status aware asynchronous response.
	 * 
	 * @param pDelegate the delegate, must not be <code>null</code>.
	 */
	public StatusAwareAsynchronousResponse(AsynchronousResponse pDelegate) {
		assert (pDelegate != null);
		delegate = pDelegate;
	}

	@Override
	public void setResponse(Response response) {
		wasAnswered = true;
		delegate.setResponse(response);
	}
	
	public boolean wasAnswered() {
		return wasAnswered;
	}

}
