package de.zapfmaster2000.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class MockClient {

	private Zapfmaster2000Service service;

	/**
	 * @param args
	 * @throws ServiceException
	 */
	public static void main(String[] args) throws Throwable {
		new MockClient().run();
	}

	public void run() throws ServiceException, MalformedURLException {

		Zapfmaster2000ServiceServiceLocator locator = new Zapfmaster2000ServiceServiceLocator();

		service = locator.getZapfmaster2000Service(new URL(
				"http://server:8080/Zapfmaster2000WebService/services/Zapfmaster2000Service"));

//		service = locator.getZapfmaster2000Service();

		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

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
						doLogin(rfid);
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
							doDraw(ticksPerUpdate);
							numTicks -= ticksPerUpdate;
							Thread.sleep(udpateEveryMs);
						}

						long now = System.currentTimeMillis();
						System.out.println("real: " + (now - old) / 1000.0);

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

	private void doLogin(final long pId) throws RemoteException {
		service.login(pId);
	}

	private void doDraw(final int pTicks) throws RemoteException {
		service.draw(pTicks);
	}
}
