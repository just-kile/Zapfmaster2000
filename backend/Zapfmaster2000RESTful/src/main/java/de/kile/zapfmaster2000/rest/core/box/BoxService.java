package de.kile.zapfmaster2000.rest.core.box;

/**
 * The Box service is used to access the different instances of Zapfmaster2000
 * Boxes that are part of the system.
 * 
 * @author Thomas Kipar
 */
public interface BoxService {

	/**
	 * Returns the draw service associated with the given passphrase
	 * 
	 * @param pBoxPassphrase
	 *            the passphrase associated with the box to get the draw manager
	 *            for
	 * @return the draw service, never <code>null</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             if no box exist for the given passphrase
	 */
	public DrawService getDrawService(String pBoxPassphrase)
			throws IllegalArgumentException;

}
