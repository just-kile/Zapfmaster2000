package de.kile.zapfmaster2000.rest.core.box;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * The draw services listener can be used to detect all activities performed for
 * a drawing-process.
 * 
 * @author Thomas Kipar
 * 
 */
public interface DrawServiceListener {

	/**
	 * Invoked when logs in to a box.
	 * 
	 * @param pUser
	 *            the user, never <code>null</code>.
	 */
	public void onLoginsuccessful(User pUser);
	
	/**
	 * Invoked when a user was logged out.
	 * 
	 * @param pUser the user that was logged out.
	 */
	public void onLogout(User pUser);

	/**
	 * Invoked when the user is drawing.
	 * 
	 * @param pUser
	 *            the user that is drawing
	 * @param pAmount
	 *            the total amount, in liters
	 */
	public void onDrawing(User pUser, double pAmount);

	/**
	 * Invoked when the user finished drawing.
	 * 
	 * @param Drawing pDrawing
	 *            the drawing that was written to db
	 */
	public void onEndDrawing(Drawing pDrawing);
	
}
