package de.zapfmaster2000.webservice;

import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;

/**
 * Zapfmaster2000 Web Service.
 * 
 * @author thomas
 */
public class Zapfmaster2000Service {

	/**
	 * Constructor.
	 */
	public Zapfmaster2000Service() {
	}

	/**
	 * Log-in a user with a given rfid id.
	 * 
	 * @param pId
	 *            the raw id fetched by the rfid reader.
	 */
	public void login(long pId) {
		Zapfmaster2000Core.getInstance().getRawInputProvider()
				.notifiyLogin(pId);
	}

	/**
	 * Process a drawing.
	 * 
	 * 
	 * @param pNumTicks
	 *            Raw number of ticks provided by the flowmeter used by the
	 *            beerometer controller.
	 */
	public void draw(int pNumTicks) {
		Zapfmaster2000Core.getInstance().getRawInputProvider()
				.notifyDraw(pNumTicks);
	}

	/**
	 * Starts a challenge (1v1)
	 * 
	 * @param pUserId1
	 *            user 1
	 * @param pUserId2
	 *            user 2
	 * @param pDuration
	 *            duration of the challenge (in minutes)
	 */
	public void startChallenge1V1(int pUserId1, int pUserId2, int pDuration) {
		Zapfmaster2000Core.getInstance().getChallengeManager()
				.createChallenge1v1(pUserId1, pUserId2, pDuration);
	}

	/**
	 * Declines a challenge.
	 * 
	 * @param pUserId
	 *            user that declined a challenge
	 * @param pText
	 *            text as reason
	 */
	public void declineChallenge(int pUserId, String pText) {
		Zapfmaster2000Core.getInstance().getChallengeManager()
				.declineChallenge(pUserId, pText);
	}

}
