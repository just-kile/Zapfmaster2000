package de.zapfmaster2000.webservice.challenge;

public interface ChallengeManager {

	public void createChallenge1v1(int pUser1, int pUser2, int pDuration);

	public void declineChallenge(int pUser, String pText);

	public void addChallangeManagerListener(ChallengeManagerListener pListener);

	public void removeChallangeManagerListener(
			ChallengeManagerListener pListener);

}
