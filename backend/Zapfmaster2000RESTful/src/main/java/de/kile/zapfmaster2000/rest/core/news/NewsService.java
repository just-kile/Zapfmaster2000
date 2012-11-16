package de.kile.zapfmaster2000.rest.core.news;

/**
 * Service to access news related sutff.
 * 
 * @author Thomas Kipar
 */
public interface NewsService {

	/**
	 * Adds a {@link NewsServiceListener}
	 * 
	 * @param pListener
	 *            listener to add
	 */
	public void addListener(NewsServiceListener pListener);

	/**
	 * Removes a {@link NewsServiceListener}.
	 * 
	 * @param pListener
	 *            listener to remove
	 */
	public void removeListener(NewsServiceListener pListener);

}
