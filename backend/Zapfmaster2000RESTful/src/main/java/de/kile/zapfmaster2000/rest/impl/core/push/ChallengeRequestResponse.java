package de.kile.zapfmaster2000.rest.impl.core.push;

public class ChallengeRequestResponse extends AbstractChallengeReponse {

	private long challengerUserId;

	private String challengerUserName;

	private String challengerImagePath;

	public ChallengeRequestResponse() {
		setType(Type.ChallengeRequest);
	}

	public long getChallengerUserId() {
		return challengerUserId;
	}

	public void setChallengerUserId(long challengerUserId) {
		this.challengerUserId = challengerUserId;
	}

	public String getChallengerImagePath() {
		return challengerImagePath;
	}

	public void setChallengerImagePath(String challengerImagePath) {
		this.challengerImagePath = challengerImagePath;
	}

	public String getChallengerUserName() {
		return challengerUserName;
	}

	public void setChallengerUserName(String challengerUserName) {
		this.challengerUserName = challengerUserName;
	}

}
