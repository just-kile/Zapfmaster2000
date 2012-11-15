package de.beeromat.internal.core.input;

import java.util.List;

import org.apache.log4j.Logger;

import de.beeromat.core.input.RawInputProviderListener;

public class DrawingTask implements Runnable {

	private static final Logger LOG = Logger.getLogger(DrawingTask.class);

	private final List<RawInputProviderListener> fListeners;

	private int fNumTicks;

	private final Object LOCK = new Object();

	private boolean fDidRunAlready = false;

	public DrawingTask(List<RawInputProviderListener> pListeners, int pNumTicks) {
		fListeners = pListeners;
		fNumTicks = pNumTicks;
	}

	public void merge(int pNumTicks) {
		synchronized (LOCK) {
			fNumTicks += fNumTicks;
		}
	}

	@Override
	public void run() {
		try {
			synchronized (LOCK) {
				if (!fDidRunAlready) {
					fDidRunAlready = true;
					for (RawInputProviderListener listener : fListeners) {
						listener.onDraw(fNumTicks);
					}
				}
			}
		} catch (Throwable th) {
			LOG.error("An error occured while calling raw input listeners", th);
			System.err
					.println("Some trouble going on here. Better call the admins.");
			th.printStackTrace();
		}
	}
}
