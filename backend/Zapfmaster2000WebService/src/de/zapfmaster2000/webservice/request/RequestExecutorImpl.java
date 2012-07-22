package de.zapfmaster2000.webservice.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import de.zapfmaster2000.webservice.Assert;

public class RequestExecutorImpl implements RequestExecutor {

	/** the logger */
	private static final Logger LOG = Logger
			.getLogger(RequestExecutorImpl.class);

	/** the url to send the requests to */
	private static final String URL = "http://server/beerometer/backend.php";
//	private static final String URL = "http://localhost/zapfmaster/backend.php";

	/** time between requests */
	private static final int UPDATE_TIME = 1000;

	/** the client */
	private HttpClient fClient;

	/** the queue for pending requests */
	private final List<PostMethod> fQueue;

	private final Date fLastRequest;

	private Timer fTimer;

	public RequestExecutorImpl() {
		fClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		fLastRequest = new Date(0);
		fQueue = new ArrayList<PostMethod>();
		fTimer = new Timer();
		fTimer.schedule(createTimerTask(), 0, UPDATE_TIME);
	}

	@Override
	public PostMethod createPostMethod(RequestType pType) {
		Assert.isNotNull(pType);
		PostMethod postMethod = new PostMethod(URL);
		postMethod.setParameter("type", pType.toString().toLowerCase());
		return postMethod;
	}

	@Override
	synchronized public void executePost(PostMethod pPostMethod) {
		synchronized (fQueue) {
			LOG.debug("Execute post: " + pPostMethod.getParameter("type"));
			if (fQueue.size() > 0) {
				// pending requests.
				PostMethod method = fQueue.get(fQueue.size() - 1);
				NameValuePair type = method.getParameter("type");
				if (type != null
						&& RequestType.DRAWING.toString().toLowerCase()
								.equals(type.getValue())) {
					NameValuePair kind = method.getParameter("kind");
					if (kind != null && "refresh".equals(kind.getValue())) {
						fQueue.remove(method);
					}

				}
			}
			fQueue.add(pPostMethod);
		}

	}

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					performRequest();
				} catch (Throwable th) {
					LOG.error("Error while performing request", th);
				}
			}
		};
	}

	private void performRequest() {
		synchronized (fQueue) {
			if (fQueue.size() > 0) {
				PostMethod method = fQueue.get(0);
				LOG.debug("Sending post request: "
						+ method.getParameter("type").getValue() + ", pending:"
						+ (fQueue.size() - 1));
				try {
					fClient = new HttpClient(
							new MultiThreadedHttpConnectionManager());
					fClient.executeMethod(method);
				} catch (HttpException ex) {
					LOG.error("Error while processing the http request", ex);
				} catch (IOException ex) {
					LOG.error("Error while processing the http request", ex);
				}
				fQueue.remove(method);
				fLastRequest.setTime(System.currentTimeMillis());
			}

		}

	}

}
