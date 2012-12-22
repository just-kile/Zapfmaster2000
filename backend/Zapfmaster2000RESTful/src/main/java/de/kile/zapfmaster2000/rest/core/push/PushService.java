package de.kile.zapfmaster2000.rest.core.push;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;

public interface PushService {

	/**
	 * Adds a request to push new news to.
	 * 
	 * @param pResponse response to push the news to
	 * @param pAccount account to push news for
	 * @param pToken token used for authentication
	 */
	public void addNewsRequest(AsynchronousResponse pResponse, Account pAccount,
			String pToken);

}
