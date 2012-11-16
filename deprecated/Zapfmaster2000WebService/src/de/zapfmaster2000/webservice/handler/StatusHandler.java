package de.zapfmaster2000.webservice.handler;

import java.util.Timer;

import org.apache.log4j.Logger;

import de.zapfmaster2000.webservice.draw.DrawManager;
import de.zapfmaster2000.webservice.draw.DrawManagerAdapter;
import de.zapfmaster2000.webservice.draw.DrawManagerListener;
import de.zapfmaster2000.webservice.input.RawInputProvider;
import de.zapfmaster2000.webservice.model.FailedLoginStatus;
import de.zapfmaster2000.webservice.model.db.User;

public class StatusHandler {

	private static final Logger LOG = Logger.getLogger(StatusHandler.class);

	final static char STATUS_OK = 1;
	final static char STATUS_ERROR = 2;
	final static char STATUS_NONE = 3;

	final static int DISPLAY_DURATION = 3 * 1000;

	private Timer fTimer;

	private char fLastStatus = STATUS_NONE;

	public StatusHandler(RawInputProvider pRawInputProvider,
			DrawManager pDrawManager) {
//		if (pRawInputProvider instanceof RawInputProviderImpl) {
//			fSerial = ((RawInputProviderImpl) pRawInputProvider).getSerial();
//			pDrawManager.addDrawManagerListener(createDrawManagerListener());
//		} else {
//			LOG.warn("Could not get serial, probably running in mock mode.");
//		}
	}

	private DrawManagerListener createDrawManagerListener() {
		return new DrawManagerAdapter() {
			@Override
			public void onLoginsuccessful(User pUser) {
				updateStatus(STATUS_OK);
				fLastStatus = STATUS_OK;
			}

			@Override
			public void onLoginFailed(FailedLoginStatus pStatus) {
				updateStatus(STATUS_ERROR);
			}

			@Override
			public void onEndDrawing(User pUser, double pAmount) {
				updateStatus(STATUS_NONE);
				fLastStatus = STATUS_NONE;

			}

		};
	}

	private void updateStatus(char pStatus) {
//		if (fTimer != null) {
//			fTimer.cancel();
//			fTimer = null;
//		}
//		fSerial.write(pStatus);
//		if (pStatus == STATUS_ERROR) {
//			fTimer = new Timer();
//			fTimer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					try {
//						updateStatus(fLastStatus);
//					} catch (Throwable th) {
//						LOG.error("Error while sending status", th);
//					}
//				}
//			}, DISPLAY_DURATION);
//		}
	}
}