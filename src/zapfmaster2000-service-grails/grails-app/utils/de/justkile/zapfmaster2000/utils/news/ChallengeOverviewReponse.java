package de.justkile.zapfmaster2000.utils.news;

public class ChallengeOverviewReponse extends Challenge1v1NewsResponse {

	private boolean challengeFinished;

	public boolean isChallengeFinished() {
		return challengeFinished;
	}

	public void setChallengeFinished(boolean challengeFinished) {
		this.challengeFinished = challengeFinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (challengeFinished ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeOverviewReponse other = (ChallengeOverviewReponse) obj;
		if (challengeFinished != other.challengeFinished)
			return false;
		return true;
	}

}
