package de.kile.zapfmaster2000.rest.core.challenge;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;

public interface ChallengeServiceListener {

	public void onPendingChallengeCreated(Challenge pChallenge);
	
	public void onChallengeDeclined(Challenge pChallenge);
	
	public void onChallengeStarted(Challenge pChallenge);
	
	public void onChallengeFinished(Challenge pChallenge);
}
