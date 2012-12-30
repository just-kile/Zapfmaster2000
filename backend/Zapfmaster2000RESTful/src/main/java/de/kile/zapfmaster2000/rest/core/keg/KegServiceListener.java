package de.kile.zapfmaster2000.rest.core.keg;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;

public interface KegServiceListener {

	/**
	 * Invoked whenever a new keg was tapped.
	 * 
	 * @param pKeg
	 */
	public void onNewKeg(Keg pKeg);
	
}
