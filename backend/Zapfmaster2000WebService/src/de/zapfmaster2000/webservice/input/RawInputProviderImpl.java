package de.zapfmaster2000.webservice.input;

import java.util.ArrayList;
import java.util.List;

public class RawInputProviderImpl implements RawInputProvider {

	/** listeners */
	private List<RawInputProviderListener> listeners = new ArrayList<RawInputProviderListener>();

	/**
	 * Adds the listener to the provider.
	 * 
	 * <p>
	 * If the listener was added before, nothing happens.
	 * </p>
	 * 
	 * @param pListener
	 *            the listener to add.
	 */
	public void addListener(RawInputProviderListener pListener) {
		listeners.add(pListener);
	}

	/**
	 * Removes a given listener from the provider.
	 * 
	 * @param pListener
	 *            the listener to remove.
	 */
	public void removeListener(RawInputProviderListener pListener) {
		listeners.remove(pListener);
	}

	public void notifiyLogin(long pId) {
		for (RawInputProviderListener listener : listeners) {
			listener.onLogin(pId);
		}
	}

	public void notifyDraw(int pTicks) {
		for (RawInputProviderListener listener : listeners) {
			listener.onDraw(pTicks);
		}
	}

}
