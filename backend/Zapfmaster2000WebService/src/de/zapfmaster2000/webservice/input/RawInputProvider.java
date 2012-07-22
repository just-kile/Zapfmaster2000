package de.zapfmaster2000.webservice.input;

/**
 * The raw input provider grabs the input from the micro-controller (collecting
 * input stuff) and distributes it.
 * 
 * <p>
 * Use {@link BeeromatCore} in order to get an instance of the raw input
 * provider.
 * </p>
 * 
 * @author thomas
 */
public interface RawInputProvider {

	/**
	 * Adds the listeenr to the provider.
	 * 
	 * <p>
	 * If the listener was added before, nothing happens.
	 * </p>
	 * 
	 * @param pListener
	 *            the listener to add.
	 */
	public void addListener(RawInputProviderListener pListener);

	/**
	 * Removes a given listener from the provider.
	 * 
	 * @param pListener
	 *            the listener to remove.
	 */
	public void removeListener(RawInputProviderListener pListener);

	public void notifiyLogin(long pId);

	public void notifyDraw(int pTicks);
	
}
