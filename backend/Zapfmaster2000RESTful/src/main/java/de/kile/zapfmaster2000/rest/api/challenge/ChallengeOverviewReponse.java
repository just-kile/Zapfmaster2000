package de.kile.zapfmaster2000.rest.api.challenge;

import de.kile.zapfmaster2000.rest.api.news.Challenge1v1NewsResponse;

public class ChallengeOverviewReponse extends Challenge1v1NewsResponse {

	private boolean challengeFinished;

	public boolean isChallengeFinished() {
		return challengeFinished;
	}

	public void setChallengeFinished(boolean challengeFinished) {
		this.challengeFinished = challengeFinished;
	}

}
