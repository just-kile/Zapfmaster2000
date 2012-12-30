package de.kile.zapfmaster2000.rest.core.keg;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;

public interface KegService {
	
	public void switchKeg(Box pBox, String pBrand, int pSize);
	
	public void addListener(KegServiceListener pListener);

	public void removeListener(KegServiceListener pListener);
	
}
