package de.justkile.zapfmaster2000.util.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import de.justkile.zapfmaster2000.util.simulator.model.AbstractRequest;
import de.justkile.zapfmaster2000.util.simulator.model.LoginRequest;
import de.justkile.zapfmaster2000.util.simulator.model.TickRequest;

public class ZapfSimulator {

	public static final String ZAPFMASTER_SERVER_URL = "http://zapfmaster2000.dyndns.org:9130/zapfmaster2000-service-1.0.0-SNAPSHOT/rest/";
//	public static final String ZAPFMASTER_SERVER_URL = "http://localhost:8080/zapfmaster2000-service/rest/";

	public static final boolean FAST_FORWARD = true;

	public static final long FAST_FORWARD_TIME = 3000;

	public final String inputPath;

	private final DefaultHttpClient httpclient = new DefaultHttpClient();

	public ZapfSimulator(String inputPath) {
		this.inputPath = inputPath;
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Expecting one argument: Input file.");
			return;
		}
		new ZapfSimulator(args[0]).run();
		System.out.println("done.");
	}

	public void run() throws Exception {
		List<AbstractRequest> model = parseInput();
		checkModel(model);
		printStats(model);
		simulate(model);
	}

	private List<AbstractRequest> parseInput() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inputPath));
		String line;

		List<AbstractRequest> result = new ArrayList<>();

		Pattern tickPattern = Pattern.compile("(\\d+) TICKS (\\d+) AT (.+)$");
		Pattern rfidPattern = Pattern.compile("(\\d+) RFID (\\d+) AT (.+)$");
		while ((line = reader.readLine()) != null) {
			Matcher tickMatcher = tickPattern.matcher(line);
			if (tickMatcher.matches()) {
				long time = Long.parseLong(tickMatcher.group(1));
				int ticks = Integer.parseInt(tickMatcher.group(2));
				String boxId = tickMatcher.group(3);

				TickRequest tickRequest = new TickRequest();
				tickRequest.setTime(time);
				tickRequest.setNumTicks(ticks);
				tickRequest.setBoxPassphrase(boxId);
				result.add(tickRequest);
			}

			Matcher rfidMatcher = rfidPattern.matcher(line);
			if (rfidMatcher.matches()) {
				long time = Long.parseLong(rfidMatcher.group(1));
				long rfid = Long.parseLong(rfidMatcher.group(2));
				String boxId = rfidMatcher.group(3);

				LoginRequest loginRequest = new LoginRequest();
				loginRequest.setTime(time);
				loginRequest.setRfidTag(rfid);
				loginRequest.setBoxPassphrase(boxId);
				result.add(loginRequest);
			}
		}

		reader.close();
		return result;
	}

	private void checkModel(List<AbstractRequest> model) throws Exception {
		for (int i = 0; i < model.size() - 1; ++i) {
			AbstractRequest r1 = model.get(i);
			AbstractRequest r2 = model.get(i + 1);
			if (r1.getTime() > r2.getTime()) {
				throw new Exception(
						"Inconsitent times: Must monotonically nondecreasing (found at "
								+ r1.getTime() + ")");
			}
		}
	}

	private void printStats(List<AbstractRequest> model) throws Exception {
		AbstractRequest firstRequest = model.get(0);
		AbstractRequest lastRequest = model.get(model.size() - 1);

		System.out.println("=== INPUT FILE STATISTICS ===");
		System.out.println("Total number of requests: " + model.size());
		System.out.println("First request at " + firstRequest.getTime() + "ms");
		System.out.println("Last request at " + lastRequest.getTime() + "ms");

		long duration = lastRequest.getTime() - firstRequest.getTime();
		System.out.println("Total simulation time: "
				+ toDurationString(duration));
	}

	private void simulate(List<AbstractRequest> model) throws Exception {
		System.out.println("=== STARTING SIMULATION ===");
		long modelStartTime = model.get(0).getTime();
		long realStartTime = System.currentTimeMillis();

		for (int i = 0; i < model.size(); ++i) {
			AbstractRequest request = model.get(i);

			long nextModelRequestTime = request.getTime();
			long now = System.currentTimeMillis();
			long deltaModelTime = nextModelRequestTime - modelStartTime;
			long deltaRealTime = now - realStartTime;

			long deltaModelTimeRealTime = deltaModelTime - deltaRealTime;
			if (deltaModelTimeRealTime > 0) {
				if (deltaModelTimeRealTime > FAST_FORWARD_TIME) {
					// jump in time! Pretend we started the sim earlier so we
					// can continue earlier as well.
					System.out.println("Time jump! "
							+ toDurationString(deltaModelTimeRealTime)
							+ " in to the past!");
					realStartTime -= (deltaModelTimeRealTime - FAST_FORWARD_TIME);
					deltaModelTimeRealTime = FAST_FORWARD_TIME;
				}
				System.out.println("Next action in " + deltaModelTimeRealTime
						+ "ms");
				Thread.sleep(deltaModelTimeRealTime);
			} else {
				System.out.println("I am late: " + deltaModelTimeRealTime);
				System.out.println(nextModelRequestTime);
			}

			performRequest(request);

		}
	}

	private void performRequest(AbstractRequest request)
			throws ClientProtocolException, IOException {
		if (request instanceof LoginRequest) {
			LoginRequest loginRequest = (LoginRequest) request;
			performRequest(loginRequest);
		} else if (request instanceof TickRequest) {
			TickRequest tickRequest = (TickRequest) request;
			performRequest(tickRequest);
		} else {
			throw new RuntimeException("Unsupported request: "
					+ request.getClass().getName());
		}

	}

	private void performRequest(LoginRequest request)
			throws ClientProtocolException, IOException {
		String url = ZAPFMASTER_SERVER_URL + "box/login?rfid="
				+ request.getRfidTag() + "&passphrase="
				+ request.getBoxPassphrase();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);

		HttpEntity entitiy = response.getEntity();
		EntityUtils.consume(entitiy);
		httpGet.releaseConnection();
	}

	private void performRequest(TickRequest request)
			throws ClientProtocolException, IOException {
		String url = ZAPFMASTER_SERVER_URL + "box/draw?ticks="
				+ request.getNumTicks() + "&passphrase="
				+ request.getBoxPassphrase();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);

		HttpEntity entitiy = response.getEntity();
		EntityUtils.consume(entitiy);
		httpGet.releaseConnection();
	}

	private String toDurationString(long duration) {
		final int millisPerSecond = 1000;
		final int millisPerMinute = 1000 * 60;
		final int millisPerHour = 1000 * 60 * 60;
		final int millisPerDay = 1000 * 60 * 60 * 24;
		int days = (int) (duration / millisPerDay);
		int hours = (int) (duration % millisPerDay / millisPerHour);
		int minutes = (int) (duration % millisPerHour / millisPerMinute);
		int seconds = (int) (duration % millisPerMinute / millisPerSecond);
		return String.format("%d days %02d hours %02d minutes %02d seconds",
				days, hours, minutes, seconds);
	}

}
