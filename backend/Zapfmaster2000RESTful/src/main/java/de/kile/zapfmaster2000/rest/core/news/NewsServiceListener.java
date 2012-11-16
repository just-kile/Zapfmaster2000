package de.kile.zapfmaster2000.rest.core.news;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;

/**
 * Listener to track news posted by the {@link NewsService}.
 * 
 * @author Thomas Kipar
 */
public interface NewsServiceListener {

	/**
	 * Invoked when a news are posted.
	 * 
	 * @param pNews
	 *            the posted news, never <code>null</code>.
	 */
	public void onNewsPosted(News pNews);

}
