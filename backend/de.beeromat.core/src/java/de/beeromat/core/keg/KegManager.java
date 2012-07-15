package de.beeromat.core.keg;

import de.beeromat.core.model.db.Keg;

public interface KegManager {

	public Keg readCurrentKeg();
	
	public void swapKeq(Keg pNewKeg);
}
