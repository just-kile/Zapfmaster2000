package de.zapfmaster2000.webservice.handler;

import de.zapfmaster2000.webservice.model.db.User;

public interface DrawingsHandlerListener {
	
	public void onDrawingFinished(User pUser);

}
