package de.beeromat.internal.core.draw;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.beeromat.core.BeeromatCoreActivator;
import de.beeromat.core.config.ConfigKeys;
import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.db.SessionFactoryUtil;
import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.draw.DrawManagerListener;
import de.beeromat.core.input.RawInputProvider;
import de.beeromat.core.input.RawInputProviderListener;
import de.beeromat.core.model.FailedLoginStatus;
import de.beeromat.core.model.FailedLoginStatus.Type;
import de.beeromat.core.model.db.User;

/**
 * Implementation of {@link DrawManager}.
 * 
 * @author thomas
 * 
 */
public class DrawManagerImpl implements DrawManager {

	private static final Logger LOG = Logger.getLogger(DrawManagerImpl.class);

	/** time until a user is loged off automatically */
	private static final int AUTO_LOGOUT_TIME = 3 * 1000;

	/** rfid tag of the guest user */
	public static final int GUEST_USER_RFID_TAG = 0;

	/** time to pass after last draw, in seconds */
	private static final long LOGIN_TIME_DIFF = 2;

	private static final Object CURRENT_USER_LOCK = new Object();

	/** the listeners */
	private final List<DrawManagerListener> fListeners = new ArrayList<DrawManagerListener>();

	/** the user that is currently drawing its beer */
	private User fCurrentUser;

	/**
	 * the guest user to use if there is a drawing taking place without prior
	 * login
	 */
	private final User fGuestUser;

	/** the logoff timer */
	private Timer fTimer;

	/** time until the last drawing took place */
	private long fLastDrawing = 0;

	/** the amount of the current draw process */
	private double fAmount;

	/**
	 * Creates the draw manager.
	 * 
	 * @param pInputProvider
	 *            the input provider to use, must not be <code>null</code>.
	 */
	public DrawManagerImpl(RawInputProvider pInputProvider) {
		Assert.isNotNull(pInputProvider);
		pInputProvider.addListener(createInputProviderListener());
		fGuestUser = findUser(GUEST_USER_RFID_TAG);
		Assert.isNotNull(fGuestUser, "No Guest User found.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addDrawManagerListener(DrawManagerListener pListener) {
		if (!fListeners.contains(pListener)) {
			fListeners.add(pListener);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeDrawManagerListener(DrawManagerListener pListener) {
		fListeners.remove(pListener);
	}

	/**
	 * Creates the input provider listener.
	 * 
	 * @return the listener, never <code>null</code>.
	 */
	private RawInputProviderListener createInputProviderListener() {
		return new RawInputProviderListener() {
			@Override
			public void onLogin(long pId) {
				doPerformLogin(pId);
			}

			@Override
			public void onDraw(int pNumTicks) {
				doPerformDraw(pNumTicks);
			}
		};
	}

	/**
	 * Invoked when a user logs on using its rfid-tag.
	 * 
	 * @param pId
	 *            the user id
	 */
	private void doPerformLogin(long pId) {
		synchronized (CURRENT_USER_LOCK) {
			if (fCurrentUser == null || fCurrentUser.getRfidTag() != pId) {
				FailedLoginStatus status = checkLoginIsAllowed(pId);
				if (status == null) {
					User newUser = findUser(pId);
					fAmount = 0;
					if (newUser == null) {
						status = new FailedLoginStatus();
						status.setType(Type.NOT_EXISTANT);
						status.setRfidId(pId);
						status.setReason("Could not find user. (rfid = " + pId
								+ ")");
						notifyOnLoginFailed(status);
					} else {
						if (fCurrentUser != null) {
							notifyOnEndDrawing(fCurrentUser, fAmount);
						}
						fCurrentUser = newUser;
						scheduleAutoLogout();
						fLastDrawing = System.currentTimeMillis();
						notifyOnLoginSuccessful(fCurrentUser);
					}
				} else {
					notifyOnLoginFailed(status);
				}
			} else {
				scheduleAutoLogout();
			}
		}
	}

	/**
	 * Checks if login is allowed.
	 * 
	 * @return <code>null</code> if the log in allowed, otherwise the reason for
	 *         reject.
	 */
	private FailedLoginStatus checkLoginIsAllowed(long pId) {
		if (fCurrentUser == null || fCurrentUser == fGuestUser) {
			return null;
		}
		double diff = System.currentTimeMillis() - fLastDrawing;
		if (diff < LOGIN_TIME_DIFF * 1000) {
			FailedLoginStatus status = new FailedLoginStatus();
			status.setReason("Another user is drawing already");
			status.setType(Type.OTHER_USER_IS_DRAWING);
			status.setRfidId(pId);
			return status;
		}
		return null;
	}

	/**
	 * Invoked when someons draws a beer.
	 * 
	 * @param pNumTicks
	 *            number of counted ticks.
	 */
	private void doPerformDraw(int pNumTicks) {
		if (pNumTicks < getMinTicks()) {
			// ignore too little tick counts, otherwise a user might not get
			// logged out even if
			// he is not doing anything
			return;
		}
		pNumTicks -= getTickReduction();

		scheduleAutoLogout();
		fLastDrawing = System.currentTimeMillis();
		double realAmountDelta = pNumTicks / getTickFactor();
		fAmount += realAmountDelta;
		// ignore to little amounts. This is most likely a "problem" of the
		// flowmeter
		if (fAmount > getMinDrawAmount()) {
			if (fCurrentUser != null) {
				notifyOnDrawing(fCurrentUser, fAmount);
			} else {
				// guest starts to draw
				fAmount = 0;
				notifyOnLoginSuccessful(fGuestUser);
				fCurrentUser = fGuestUser;
				notifyOnDrawing(fGuestUser, fAmount);
				scheduleAutoLogout();
			}
		}
	}

	/**
	 * Notifiess the listerers that a user logged in sucessfully.
	 * 
	 * @param pUser
	 *            the user.
	 */
	private void notifyOnLoginSuccessful(User pUser) {
		LOG.debug("Login succeded, user: " + pUser.getName());
		for (DrawManagerListener listener : fListeners) {
			try {
				listener.onLoginsuccessful(pUser);
			} catch (Throwable th) {
				LOG.error("Error while processing login successful event", th);
			}
		}
	}

	/**
	 * Notifiess the listerers that a login failed
	 * 
	 * @param pReason
	 *            the reeason for the fail.
	 */
	private void notifyOnLoginFailed(FailedLoginStatus pStatus) {
		LOG.debug("Login failed: " + pStatus.getReason());
		for (DrawManagerListener listener : fListeners) {
			try {
				listener.onLoginFailed(pStatus);
			} catch (Throwable th) {
				LOG.error("Error while processing login failed event", th);
			}
		}
	}

	private void notifyOnDrawing(User pUser, double pAmount) {
		for (DrawManagerListener listener : fListeners) {
			try {
				listener.onDrawing(pUser, pAmount);
			} catch (Throwable th) {
				LOG.error("Error while processing drawingl event", th);
			}
		}
	}

	private void notifyOnEndDrawing(User pUser, double pAmount) {
		for (DrawManagerListener listener : fListeners) {
			try {
				listener.onEndDrawing(pUser, pAmount);
			} catch (Throwable th) {
				LOG.error("Error while processing end drawin event", th);
			}
		}
	}

	/**
	 * Queries the db to check if there is a user with the given rfid id.
	 * 
	 * @param pId
	 *            the rfid tag id.
	 * @return the user with the given rfid id or <code>null</code> if not
	 *         found.
	 */
	private User findUser(long pId) {
		Transaction tx = null;
		User user = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		try {
			tx = session.beginTransaction();
			List<?> users = session.createQuery(
					"select u from User as u where u.rfidTag = " + pId).list();

			if (users.size() == 1) {
				user = (User) users.get(0);
			}
			tx.commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
				}
				// throw again the first exception
				throw ex;
			}

		}
		return user;
	}

	private void scheduleAutoLogout() {
		if (fTimer != null) {
			fTimer.cancel();
		}
		fTimer = new Timer();
		fTimer.schedule(createTimerTask(), AUTO_LOGOUT_TIME);
	}

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					synchronized (CURRENT_USER_LOCK) {
						if (fCurrentUser != null) {
							LOG.info("Auto-Logout for user: "
									+ fCurrentUser.getName());
							notifyOnEndDrawing(fCurrentUser, fAmount);
							fCurrentUser = null;
						}
					}
				} catch (Throwable th) {
					LOG.error("Error while performing auto log out", th);
				}
			}
		};
	}

	private double getMinDrawAmount() {
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.MIN_DRAW_AMOUNT);
	}

	private int getMinTicks() {
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getInt(ConfigKeys.MIN_TICKS);
	}

	private int getTickReduction() {
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getInt(ConfigKeys.TICK_REDUCTION);
	}

	private double getTickFactor() {
		ConfigManager configManager = BeeromatCoreActivator.getDefault()
				.getCore().getConfigManager();
		return configManager.getDouble(ConfigKeys.TICK_FACTOR);
	}

}
