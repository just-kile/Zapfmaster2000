package de.kile.zapfmaster2000.rest.core.challenge;

import java.util.List;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * Service to deal with challenges.
 * 
 * @author Thomas Kipar
 */
public interface ChallengeService {

	/**
	 * Remembers a user. This will cause
	 * 
	 * @param pUser
	 *            the user
	 * @param pResponse
	 *            the asynchronous response uses by the user to get challenge
	 *            notifications.
	 */
	public void rememberUser(User pUser,
			StatusAwareAsynchronousResponse pResponse);

	/**
	 * Creates a new pending 1v1 challenge.
	 * 
	 * @param pUser1
	 *            user 1 (challenger)
	 * @param pUser2
	 *            user 2 (challengee)
	 * @param pDuration
	 *            duration of the challenge (in minutes)
	 * @return the newly created, pending challenge
	 */
	public Challenge1v1 createPendingChallenge1v1(User pUser1, User pUser2,
			int pDuration);

	/**
	 * Starts a pending challenge.
	 * 
	 * @param pChallenge
	 *            challenge to start.
	 */
	public void startChallenge(Challenge pChallenge);

	/**
	 * Declines a pending challenge.
	 * 
	 * @param pChallenge
	 *            challenge to decline.
	 */
	public void declineChallenge(Challenge pChallenge);

	/**
	 * Retrieves the users that are logged in (to receive challenge
	 * notifications).
	 * 
	 * @param pAccount
	 *            the account to retrieve users for
	 * @return list of users
	 */
	public List<User> retrieveLoggedInUsers(Account pAccount);

	public void addListener(ChallengeServiceListener pListener);

	public void removeListener(ChallengeServiceListener pListener);

}
