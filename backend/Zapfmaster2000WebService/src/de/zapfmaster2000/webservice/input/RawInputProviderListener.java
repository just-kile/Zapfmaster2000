package de.zapfmaster2000.webservice.input;

/**
 * The raw input provider listener is used to receive changes with help of the
 * {@link RawInputProvider}.
 * 
 * @author thomas
 */
public interface RawInputProviderListener {

	/**
	 * Invoked when somebody logged in to the system.
	 * 
	 * @param pId
	 *            the raw id fetched by the rfid reader.
	 */
	public void onLogin(long pId);

	/**
	 * Invoked when somebody drawed some delecious beer.
	 * 
	 * 
	 * @param pNumTicks
	 *            Raw number of ticks provided by the flowmeter used by the
	 *            beerometer controller.
	 */
	public void onDraw(int pNumTicks);

}
