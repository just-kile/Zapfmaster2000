package de.beeromat.core.draw;

import de.beeromat.core.model.FailedLoginStatus;
import de.beeromat.core.model.db.User;

/**
 * Default draw manager adapter that does nothing as default action.
 * 
 * @author thomas
 */
public class DrawManagerAdapter implements DrawManagerListener {

	@Override
	public void onLoginsuccessful(User pUser) {
	}

	@Override
	public void onLoginFailed(FailedLoginStatus pStatus) {
	}

	@Override
	public void onDrawing(User pUser, double pAmount) {
	}

	@Override
	public void onEndDrawing(User pUser, double pAmount) {
	}

}
