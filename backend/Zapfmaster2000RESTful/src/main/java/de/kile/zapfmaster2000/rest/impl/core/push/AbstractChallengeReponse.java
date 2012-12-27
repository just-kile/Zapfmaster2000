package de.kile.zapfmaster2000.rest.impl.core.push;

public abstract class AbstractChallengeReponse {

	private long challengeId;

	private Type type;

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		ChallengeRequest, ChallengeAccepted, ChallengeDeclined
	}

}
