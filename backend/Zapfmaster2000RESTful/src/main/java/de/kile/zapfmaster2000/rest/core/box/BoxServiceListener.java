package de.kile.zapfmaster2000.rest.core.box;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * Listener to detect stuff going on with a single Box.
 * 
 * @author Thomas Kipar
 */
public interface BoxServiceListener {

	/**
	 * Invoked when a log was successful for a box.
	 * 
	 * @param pBox
	 *            the box the login succeeded for
	 * @param pUser
	 *            the user, never <code>null</code>.
	 */
	public void onLoginsuccessful(Box pBox, User pUser);

	/**
	 * Invoked when a log out occurred.
	 * 
	 * @param pBox
	 *            the box the logout was performed for
	 * @param pUser
	 *            the user that logged off
	 */
	public void onLogout(Box pBox, User pUser);

	/**
	 * Invoked when the user is drawing.
	 * 
	 * @param pBox
	 *            the box the drawing occurred for
	 * @param pUser
	 *            the user that is drawing
	 * @param pAmount
	 *            the total amount, in liters
	 */
	public void onDrawing(Box pBox, User pUser, double pAmount);

	/**
	 * Invoked when the user finished drawing.
	 * 
	 * @param pBox
	 *            the box the drawing was finished at
	 * @param Drawing
	 *            pDrawing the drawing that was written to the db
	 */
	public void onEndDrawing(Box pBox, Drawing pDrawing);

}
