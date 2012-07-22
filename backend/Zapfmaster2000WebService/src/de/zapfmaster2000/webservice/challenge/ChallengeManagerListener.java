package de.zapfmaster2000.webservice.challenge;

import de.zapfmaster2000.webservice.model.db.Challenge;

public interface ChallengeManagerListener {
	
	/**
	 * Invoked when a new challenge was created.
	 * 
	 * @param pChallenge newly created challenge
	 */
	public void onChallengeCreated(Challenge pChallenge);
	
	/**
	 * Invoked when a user declined a challenge.
	 * 
	 * @param pUserId the user that decline the challenge
	 */
	public void onChallengeDeclined(int pUserId);

}
