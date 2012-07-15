package de.beeromat.internal.core.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import de.beeromat.core.input.RawInputProvider;
import de.beeromat.core.input.RawInputProviderListener;

/**
 * Mock implementation for the {@link RawInputProvider}.
 * 
 * <p>
 * Used for testing only.
 * </p>
 * 
 * @author thomas
 */
public class RawInputProviderMock implements RawInputProvider {

	private static final Logger LOG = Logger
			.getLogger(RawInputProviderMock.class);

	private ThreadPoolExecutor fExecutor = (ThreadPoolExecutor) Executors
			.newScheduledThreadPool(1);

	/** listeners */
	private final List<RawInputProviderListener> fListeners = new ArrayList<RawInputProviderListener>();

	public RawInputProviderMock() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));

				while (true) {
					try {
						String line = reader.readLine();
						String[] tokens = line.split(" ");
						if ("login".equals(tokens[0])) {
							// login
							if (tokens.length != 2) {
								System.out
										.println("Need exaclty one parameter for login");
							} else {
								long rfid = Long.parseLong(tokens[1]);
								notifyListenersLogin(rfid);
							}
						} else if ("draw".equals(tokens[0])) {
							// draw
							if (tokens.length != 3) {
								System.out
										.println("Need exaclty two parameters for draw");
							} else {

								final int udpateEveryMs = 1000;
								final int ticksPerLiter = 6000;

								double amount = Double.parseDouble(tokens[1]);
								double duration = Double.parseDouble(tokens[2]);

								int numTicks = (int) (amount * ticksPerLiter);
								int ticksPerUpdate = (int) ((numTicks / duration) * (udpateEveryMs / 1000.0));

								long old = System.currentTimeMillis();

								while (numTicks > 0) {
									notifiyListenersDraw(ticksPerUpdate);
									numTicks -= ticksPerUpdate;
									Thread.sleep(udpateEveryMs);
								}

								long now = System.currentTimeMillis();
								System.out.println("real: " + (now - old)
										/ 1000.0);

							}
							System.out.println("finished drawing.");
						} else {
							System.out.println("Unkonwn command: " + tokens[0]);
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(RawInputProviderListener pListener) {
		if (pListener != null && !fListeners.contains(pListener)) {
			fListeners.add(pListener);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(RawInputProviderListener pListener) {
		fListeners.remove(pListener);
	}

	/**
	 * Notifies all listernes that a user logged in.
	 */
	private void notifyListenersLogin(final long pId) {
		Runnable task = new LoginTask(fListeners, pId);
		fExecutor.execute(task);
		LOG.info("Task Queue: " + fExecutor.getQueue().size());

	}

	private void notifiyListenersDraw(final int pTicks) {
		BlockingQueue<Runnable> queue = fExecutor.getQueue();
		boolean merged = false;
		if (queue.size() > 2) {
			try {
				Object[] rawObjects = queue.toArray();
				FutureTask<?> lastTask = (FutureTask<?>) rawObjects[rawObjects.length - 1];
				if (lastTask.get() instanceof DrawingTask) {
					DrawingTask task = (DrawingTask) lastTask.get();
					task.merge(pTicks);
					merged = true;
				}
			} catch (Throwable th) {
				LOG.error("Error while merging", th);
			}
		}

		if (!merged) {
			Runnable task = new DrawingTask(fListeners, pTicks);
			fExecutor.execute(task);
		}
		LOG.info("Task Queue: " + queue.size());
	}

}
