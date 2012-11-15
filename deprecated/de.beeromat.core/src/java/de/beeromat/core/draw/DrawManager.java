package de.beeromat.core.draw;

/**
 * The draw manager provides all logic related to a full draw process: Log-In,
 * collecting the amount of beer drawed and finishing the draw.
 * 
 * @author thomas
 * 
 */
public interface DrawManager {
	
	/**
	 * Adds a listener to the draw manager.
	 * 
	 * @param pListener the listener to add to the manager.
	 */
	public void addDrawManagerListener(DrawManagerListener pListener);
	
	/**
	 * Removes a listener from the draw manager.
	 * 
	 * @param pListener listener to remove.
	 */
	public void removeDrawManagerListener(DrawManagerListener pListener);

}
