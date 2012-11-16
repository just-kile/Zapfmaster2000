package de.kile.zapfmaster2000.rest.core.box;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * The draw service handles all stuff related to a single Zapfmaster2000-Box.
 * This is to log in for a user (via rfid card) or to handle drawing actions
 * performed for the specific box.
 * 
 * @author Thomas Kipar
 */
public interface DrawService {

	/**
	 * Invoked when the user logs in with his rfid card on a box.
	 * 
	 * @param pRfidId
	 *            rfid code
	 * @param the
	 *            user that was logged in successfully or <code>null</code> if
	 *            the login did not succeed
	 */
	public User login(long pRfidId);

	/**
	 * Invoked when someone draws at a box.
	 * 
	 * @param pRawAmount
	 *            raw draw amount (in ticks)
	 */
	public void draw(int pRawAmount);

	/**
	 * Returns the box this service is working for.
	 * 
	 * @return the box, never <code>null</code>.
	 */
	public Box getBox();

	/**
	 * Adds a listener to the draw manager
	 * 
	 * @param pListener
	 *            listener to add
	 */
	public void addListener(DrawServiceListener pListener);

	/**
	 * Removes a draw manager listener
	 * 
	 * @param pListener
	 *            listener to remove
	 */
	public void removeListener(DrawServiceListener pListener);

}
