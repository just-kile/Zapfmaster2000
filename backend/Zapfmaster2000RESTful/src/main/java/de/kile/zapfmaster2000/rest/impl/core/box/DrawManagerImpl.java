package de.kile.zapfmaster2000.rest.impl.core.box;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.attribute.standard.MediaSize.Other;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.DrawManager;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationConstants;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class DrawManagerImpl implements DrawManager {

	/** logger */
	private static final Logger LOG = Logger.getLogger(DrawManagerImpl.class);

	/** sync for user changes */
	private final Object SYNC_USER_LOCK = new Object();

	/** the box the manager is used for */
	private final Box box;

	/** user that is currently logged in at this box */
	private User currentUser;

	/** the guest user */
	private User guestUser;

	/** time when the last drawing action was performed */
	private long lastDrawing;

	/** amount of the current draw process */
	private double amount;

	/** the timer */
	private Timer timer;

	/** listeners */
	private final List<DrawManagerListener> listeners = new ArrayList<>();

	public DrawManagerImpl(Box pBox) {
		assert (pBox != null);
		box = pBox;
	}

	@Override
	public User login(long pRfidId) {
		synchronized (SYNC_USER_LOCK) {
			if (currentUser == null || currentUser.getRfidTag() != pRfidId) {

				// new user logs in
				if (checkNewUserCanLogin(pRfidId)) {
					User newUser = readUser(pRfidId);
					if (newUser != null) {
						// login succeeded
						if (currentUser != null) {
							notifyEndDrawing(currentUser, amount);
						}

						currentUser = newUser;
						scheduleAutoLogout();
						lastDrawing = System.currentTimeMillis();
						notifyLoginSuccessful(currentUser);
						return currentUser;
					}
				}
			} else {
				// current user logs in
				scheduleAutoLogout();
				return currentUser;
			}
		}
		
		// log did fail
		return null;
	}

	@Override
	public void draw(int pRawAmount) {
		// TODO Auto-generated method stub

	}

	@Override
	public Box getBox() {
		return box;
	}

	@Override
	public void addListener(DrawManagerListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(DrawManagerListener pListener) {
		listeners.remove(pListener);
	}

	/**
	 * Checks if a new user may login to the this box.
	 * 
	 * @return <code>true</code> if a login is allowed, <code>false</code>
	 *         otherwise.
	 */
	private boolean checkNewUserCanLogin(long pId) {
		if (currentUser == null || currentUser == guestUser) {
			return true;
		}
		double diff = System.currentTimeMillis() - lastDrawing;
		if (diff < Zapfmaster2000Core.INSTANCE.getConfigurationManager()
				.getInt(ConfigurationConstants.BOX_LONGIN_MIN_DIFF)) {
			return false;
		}
		return true;
	}

	/**
	 * Reads the user from database.
	 * 
	 * @param pRfidId
	 *            rfid tag of the user to find
	 * @return the user or <code>null</code> if there is no such user.
	 */
	private User readUser(long pRfidId) {
		// query database for box manager otherwisse
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionManager()
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> users = session
					.createQuery("SELECT u FROM User WHERE u.rfid = :rfid")
					.setLong("rfid", pRfidId).list();
			if (users.size() != 1) {
				return null;
			}
			return users.get(0);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	private void notifyLoginSuccessful(User pUser) {
		for (DrawManagerListener listener : listeners) {
			listener.onLoginsuccessful(pUser);
		}
	}

	private void notifyDrawing(User pUser, double pAmount) {
		for (DrawManagerListener listener : listeners) {
			listener.onDrawing(pUser, pAmount);
		}
	}

	private void notifyEndDrawing(User pUser, double pAmount) {
		for (DrawManagerListener listener : listeners) {
			listener.onEndDrawing(pUser, pAmount);
		}
	}

	private void scheduleAutoLogout() {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		int time = Zapfmaster2000Core.INSTANCE.getConfigurationManager()
				.getInt(ConfigurationConstants.BOX_LONGIN_AUTO_LOGOUT);
		timer.schedule(createTimerTask(), time);
	}

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					synchronized (SYNC_USER_LOCK) {
						if (currentUser != null) {
							LOG.info("Auto-Logout for user: "
									+ currentUser.getName());
							notifyEndDrawing(currentUser, amount);
							currentUser = null;
						}
					}
				} catch (Throwable th) {
					LOG.error("Error while performing auto log out", th);
				}
			}
		};
	}

}
