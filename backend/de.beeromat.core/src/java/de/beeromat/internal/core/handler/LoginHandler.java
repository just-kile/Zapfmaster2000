package de.beeromat.internal.core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;

import de.beeromat.core.draw.DrawManager;
import de.beeromat.core.draw.DrawManagerAdapter;
import de.beeromat.core.draw.DrawManagerListener;
import de.beeromat.core.input.RawInputProvider;
import de.beeromat.core.input.RawInputProviderListener;
import de.beeromat.core.model.FailedLoginStatus;
import de.beeromat.core.model.db.User;
import de.beeromat.internal.core.draw.DrawManagerImpl;
import de.beeromat.internal.core.model.LoginDescriptor;

public class LoginHandler {

	private static final Logger LOG = Logger.getLogger(LoginHandler.class);

	/** the url to send the requests to */
	private static final String URL = "http://zoidberg.dyndns-at-home.com/beerometer/rfid.php";
	//private static final String URL = "http://localhost/zapfmaster/rfid.php";

	private static final String PARAM_USER_ID = "USER_ID";

	private static final String PARAM_RFID_TAG = "RFID_TAG";

	private static final int PARAM_VALUE_UNSET = -1;

	private static final int INTERVALL = 1000;

	private User fCurrentUser = null;

	private final Timer fTimer;

	private final List<LoginDescriptor> fQueue = new ArrayList<LoginDescriptor>();

	public LoginHandler(RawInputProvider pInputProvider,
			DrawManager pDrawManager) {
		Assert.isNotNull(pInputProvider);
		Assert.isNotNull(pDrawManager);

		pInputProvider.addListener(createRawInputProviderListener());
		pDrawManager.addDrawManagerListener(createDrawManagerListener());

		fTimer = new Timer();
		fTimer.schedule(createTimerTask(), 0, INTERVALL);

	}

	private RawInputProviderListener createRawInputProviderListener() {
		return new RawInputProviderListener() {

			@Override
			public void onLogin(long pId) {
				if (fCurrentUser != null && fCurrentUser.getRfidTag() == pId) {
					LoginHandler.this.onLogin(fCurrentUser);
				}
			}

			@Override
			public void onDraw(int pNumTicks) {
			}
		};

	}

	private DrawManagerListener createDrawManagerListener() {
		return new DrawManagerAdapter() {

			@Override
			public void onEndDrawing(User pUser, double pAmount) {
				onLogout();
			}

			@Override
			public void onLoginsuccessful(User pUser) {
				onLogin(pUser);
			}

			@Override
			public void onLoginFailed(FailedLoginStatus pStatus) {
				onLogin(pStatus.getRfidId());
			}

		};
	}

	private void onLogin(User pUser) {
		fCurrentUser = pUser;
		sendPostRequest(pUser.getUserId(), pUser.getRfidTag());
	}

	private void onLogin(long pId) {
		sendPostRequest(PARAM_VALUE_UNSET, pId);
	}

	private void onLogout() {
		fCurrentUser = null;
		sendPostRequest(PARAM_VALUE_UNSET, DrawManagerImpl.GUEST_USER_RFID_TAG);
	}

	private void sendPostRequest(int pUserId, long pRfidTag) {
		synchronized (fQueue) {
			fQueue.clear();
			LoginDescriptor descriptor = new LoginDescriptor();
			descriptor.setRfidId(pRfidTag);
			descriptor.setUserId(pUserId);
			fQueue.add(descriptor);

		}
	}

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					LoginDescriptor head = null;
					synchronized (fQueue) {
						if (!fQueue.isEmpty()) {
							head = fQueue.get(0);
							fQueue.remove(0);
						}
					}

					if (head != null) {
						LOG.debug("Sending post, user=" + head.getUserId()
								+ ", rfid=" + head.getRfidId());
						HttpClient client = new HttpClient(
								new MultiThreadedHttpConnectionManager());
						PostMethod postMethod = new PostMethod(URL);
						postMethod.setParameter(PARAM_USER_ID,
								String.valueOf(head.getUserId()));
						postMethod.setParameter(PARAM_RFID_TAG,
								String.valueOf(head.getRfidId()));

						try {
							client.executeMethod(postMethod);
						} catch (HttpException ex) {
							LOG.error("Error while sending post request", ex);
						} catch (IOException ex) {
							LOG.error("Error while sending post request", ex);
						}
					}
				} catch (Throwable th) {
					LOG.error("Error while handling login", th);
				}
			}
		};
	}
}