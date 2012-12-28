package de.kile.zapfmaster2000.rest.impl.core.push;

public class ChallengeAcceptedReponse extends AbstractChallengeReponse {

	private long user1Id;

	private long user2Id;

	private String user1Name;

	private String user2Name;

	public ChallengeAcceptedReponse() {
		setType(Type.ChallengeAccepted);
	}

	public long getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(long user1Id) {
		this.user1Id = user1Id;
	}

	public long getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(long user2Id) {
		this.user2Id = user2Id;
	}

	public String getUser1Name() {
		return user1Name;
	}

	public void setUser1Name(String user1Name) {
		this.user1Name = user1Name;
	}

	public String getUser2Name() {
		return user2Name;
	}

	public void setUser2Name(String user2Name) {
		this.user2Name = user2Name;
	}

}
