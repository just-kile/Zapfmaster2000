package de.zapfmaster2000.webservice.draw;

import de.zapfmaster2000.webservice.model.FailedLoginStatus;
import de.zapfmaster2000.webservice.model.db.User;

/**
 * The draw maager listener can be used to detect all activities performed for a
 * drawing-process.
 * 
 * @author thomas
 * 
 */
public interface DrawManagerListener {
		
	/**
	 * Invoked when logs in to the system.
	 * 
	 * @param pUser the user, never <code>null</code>.
	 */
	public void onLoginsuccessful(User pUser);
	
	/**
	 * Invokes when the login failed.
	 * 
	 * @param pReason the reason why the login failed.
	 */
	public void onLoginFailed(FailedLoginStatus pStatus);
	
	/**
	 * Invoked when the user is drawing.
	 * 
	 * @param pUser the user that is drawing
	 * @param pAmount the total amount, in liters
	 */
	public void onDrawing(User pUser, double pAmount);
	
	/**
	 * Invoked when the user finished drawing.
	 * 
	 * @param pUser the user that finish drawing
	 * @param pAmount the amount the user draw.
	 */
	public void onEndDrawing(User pUser, double pAmount);

}
