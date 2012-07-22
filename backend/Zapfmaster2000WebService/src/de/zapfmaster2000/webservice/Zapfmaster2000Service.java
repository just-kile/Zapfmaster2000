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
		Zapfmaster2000Core.getInstance().getRawInputProvider().notifiyLogin(pId);
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
		Zapfmaster2000Core.getInstance().getRawInputProvider().notifyDraw(pNumTicks);
	}



}
