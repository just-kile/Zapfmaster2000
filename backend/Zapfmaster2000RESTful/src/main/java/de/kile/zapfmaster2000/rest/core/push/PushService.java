package de.kile.zapfmaster2000.rest.core.push;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public interface PushService {

	/**
	 * Adds a request to push new news to.
	 * 
	 * @param pResponse
	 *            response to push the news to
	 * @param pAccount
	 *            account to push news for
	 * @param pToken
	 *            token used for authentication
	 */
	public void addNewsRequest(AsynchronousResponse pResponse,
			Account pAccount, String pToken);

	/**
	 * Adds a request to push draft kit updates to
	 * 
	 * @param pResponse
	 *            response to push updates to
	 * @param pBox
	 *            box to push news for
	 * @param pToken
	 *            token used for authentication
	 */
	public void addDraftkitRequest(AsynchronousResponse pResponse, Box pBox,
			String pToken);

	/**
	 * Adds a request to push challenge updates to
	 * 
	 * @param pResponse
	 *            response to push updates to
	 * @param pUser
	 *            user to push updates for
	 */
	public void addChallengeRequest(AsynchronousResponse pResponse, User pUser);

	/**
	 * Ads a request to push unknown rfid tags to.
	 * 
	 * @param pResponse
	 *            response to push tags to
	 * @param pBox
	 *            box to push tags for
	 */
	public void addUnkownRfidRequest(AsynchronousResponse pResponse, Box pBox);

}
