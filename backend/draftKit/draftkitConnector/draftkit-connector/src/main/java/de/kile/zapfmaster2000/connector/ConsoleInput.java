package de.kile.zapfmaster2000.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * provides a console interface, with which the program can be started and certain parameters can be configured
 * 
 * @author wittekind
 *
 */
public class ConsoleInput implements Runnable {

	private Connector connector;
	
	public ConsoleInput(Connector newConnector) {
		connector = newConnector;
	}
	
	@Override
	public void run() {
		
		boolean running = true;
		
		showOptions();

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(is);
		
		while (running) {
			try {
				String command = in.readLine();
				String[] segments = command.split(" ");

				switch (segments[0]) {
				case "start":
					connector.start();
					break;
				case "interval":
					assertParameterCount(segments, 1);
					connector.setDefaultInterval(Integer.parseInt(segments[1]));
					connector.sendNewInterval();
					System.out.println("New interval is: "+connector.getDefaultInterval());
					break;
				case "serial":
					assertParameterCount(segments, 1);
					connector.setSerialPort(segments[1]);
					System.out.println("New serial port is: "+connector.getSerialPort());
					break;
				case "help":
					showOptions();
					break;
				case "exit":
					running = false;
					break;
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * print available user options to console
	 */
	public void showOptions() {
		System.out.println("Commands: ");
		System.out.println("\tstart connector: start");
		System.out.println("\tset a new tick count interval ["
				+ connector.getDefaultInterval() + "]: interval <int>");
		System.out.println("\tset a new serial port [" + connector.getSerialPort()
				+ "]: serial <portname>");
		System.out.println("\tshow program options: help");
		System.out.println("\tclose the program: exit");
	}
	
	/**
	 * assert amount of parameters for chosen option
	 * 
	 * @param pCommand
	 * @param pExpectedCount
	 */
	private static void assertParameterCount(String[] pCommand,
			int pExpectedCount) {
		if ((pCommand.length - 1) != pExpectedCount) {
			throw new IllegalArgumentException("Wrong parmaeter count: "
					+ pExpectedCount + " parameter expected.");
		}
	}

}
