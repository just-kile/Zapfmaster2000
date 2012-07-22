package de.zapfmaster2000.webservice.keg;

import de.zapfmaster2000.webservice.model.db.Keg;

public interface KegManager {

	public Keg readCurrentKeg();
	
	public void swapKeq(Keg pNewKeg);
}
