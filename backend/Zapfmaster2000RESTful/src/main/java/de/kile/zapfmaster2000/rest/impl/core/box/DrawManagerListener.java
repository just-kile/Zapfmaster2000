package de.kile.zapfmaster2000.rest.impl.core.box;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

/**
 * The draw manager listener can be used to detect all activities performed for
 * a drawing-process.
 * 
 * @author Thomas Kipar
 * 
 */
public interface DrawManagerListener {

	/**
	 * Invoked when logs in to a box.
	 * 
	 * @param pUser
	 *            the user, never <code>null</code>.
	 */
	public void onLoginsuccessful(User pUser);

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
	 * @param pUser
	 *            the user that finish drawing
	 * @param pAmount
	 *            the amount the user draw.
	 */
	public void onEndDrawing(User pUser, double pAmount);

}
