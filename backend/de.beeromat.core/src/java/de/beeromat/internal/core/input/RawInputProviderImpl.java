package de.beeromat.internal.core.input;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import de.beeromat.core.input.RawInputProvider;
import de.beeromat.core.input.RawInputProviderListener;

public class RawInputProviderImpl implements RawInputProvider {

	/** logger */
	private static final Logger LOG = Logger
			.getLogger(RawInputProviderImpl.class);

	private final static char LOGIN_HEADER = 13;

	private final static char DRAW_HEADER = 120;

	private final static char END_MESSAGE = '\n';

	private final static int MAX_MESSAGE_SIZE = 10;

	private final static int BAUDRATE = 115200;

	private final int LOGIN_INTERVAL = 1 * 1000;

	private final static String DEVICE_NAME = "/dev/ttyACM0";
	//private final static String DEVICE_NAME = "COM6";

	private final List<RawInputProviderListener> fListeners = new ArrayList<RawInputProviderListener>();

	private Serial fSerial;

	private final List<Byte> fBuffer = new ArrayList<Byte>();

	private final ThreadPoolExecutor fExecutor = (ThreadPoolExecutor) Executors
			.newScheduledThreadPool(1);

	private long lastLogin = 0;

	public RawInputProviderImpl() {
		try {
			fSerial = new Serial(DEVICE_NAME, BAUDRATE, '0', 8, 1);
			fSerial.addListener(createListener());
		} catch (SerialException e) {
			e.printStackTrace();
			System.err
					.println("No input, no competition. Sorry guys, I better stop right away.");
			System.exit(-1);
		}
	}

	@Override
	public void addListener(RawInputProviderListener pListener) {
		fListeners.add(pListener);
	}

	@Override
	public void removeListener(RawInputProviderListener pListener) {
		fListeners.remove(pListener);
	}

	public Serial getSerial() {
		return fSerial;
	}

	private MessageConsumer createListener() {
		return new MessageConsumer() {
			@Override
			public void message(String pString) {
				processMessage(pString);
			}
		};
	}

	private void processMessage(String pString) {

		// add bytes to buffer
		for (byte newByte : pString.getBytes()) {
			fBuffer.add(newByte);
		}

		// try to parse messages
		boolean done = false;
		while (!done && fBuffer.size() > 0) {
			byte firstByte = fBuffer.get(0);
			if (firstByte == DRAW_HEADER) {
				// draw. find end byte
				int endIndex = findEnd();
				if (endIndex > 0) {
					// complete draw message received
					char[] rawNumber = extract(1, endIndex);
					String stringNumer = String.valueOf(rawNumber);
					int pTicks = Integer.valueOf(stringNumer);
					removeFromBuffer(endIndex + 1);
					notifyListenersDraw(pTicks);
				} else if (fBuffer.size() > MAX_MESSAGE_SIZE) {
					fBuffer.remove(0);
					LOG.warn("Message to long, dropping first byte");
				} else {
					done = true;
				}
			} else if (firstByte == LOGIN_HEADER) {
				// draw. find end byte
				int endIndex = findEnd();
				if (endIndex > 0) {
					char[] rawNumber = extract(1, endIndex);
					long id = 0;
					for (int i = 0; i < rawNumber.length; ++i) {
						id += rawNumber[i];
						id <<= 8;
					}
					removeFromBuffer(endIndex + 1);
					long now = System.currentTimeMillis();
					long diff = now - lastLogin;
					if (diff > LOGIN_INTERVAL) {
						lastLogin = now;
						notifyListenersLogin(id);
					}
				} else if (fBuffer.size() > MAX_MESSAGE_SIZE) {
					fBuffer.remove(0);
					LOG.warn("Message to long, dropping first byte");
				} else {
					done = true;
				}
			} else {
				LOG.warn("Unexpexted input: " + firstByte);
				fBuffer.remove(0);
			}
		}
	}

	/**
	 * Notifies all listernes that a user logged in.
	 */
	private void notifyListenersLogin(long pId) {
		try {
			LOG.debug("Fetched login, id = " + pId);

			Runnable task = new LoginTask(fListeners, pId);
			fExecutor.execute(task);
			LOG.info("Task Queue: " + fExecutor.getQueue().size());
		} catch (Throwable th) {
			LOG.error("Error while processing raw login message", th);
		}
	}

	private void notifyListenersDraw(int pTicks) {
		try {
			LOG.debug("Fetched draw, num ticks = " + pTicks);

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
		} catch (Throwable th) {
			LOG.error("Error while processing raw draw message", th);
		}
	}

	private int findEnd() {
		for (int i = 1; i < fBuffer.size(); ++i) {
			if (fBuffer.get(i) == END_MESSAGE) {
				return i;
			}
		}
		return -1;
	}

	private char[] extract(int begin, int end) {
		int length = end - begin;
		char[] chars = new char[length];
		for (int i = 0; i < length; ++i) {
			chars[i] = (char) fBuffer.get(begin + i).intValue();
		}
		return chars;
	}

	private void removeFromBuffer(int pNum) {
		for (int i = 0; i < pNum; ++i) {
			fBuffer.remove(0);
		}
	}

}
