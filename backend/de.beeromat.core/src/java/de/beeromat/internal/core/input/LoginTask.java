package de.beeromat.internal.core.input;

import java.util.List;

import org.apache.log4j.Logger;

import de.beeromat.core.input.RawInputProviderListener;

public class LoginTask implements Runnable {

	private static final Logger LOG = Logger.getLogger(LoginTask.class);

	private final List<RawInputProviderListener> fListeners;

	private final long fId;

	public LoginTask(List<RawInputProviderListener> pListeners, long pId) {
		fListeners = pListeners;
		fId = pId;
	}

	@Override
	public void run() {
		try {
			for (RawInputProviderListener listener : fListeners) {
				listener.onLogin(fId);
			}
		} catch (Throwable th) {
			LOG.error("An error occured while calling raw input listeners", th);
			System.err
					.println("Some trouble going on here. Better call the admins.");
			th.printStackTrace();
		}
	}

}
