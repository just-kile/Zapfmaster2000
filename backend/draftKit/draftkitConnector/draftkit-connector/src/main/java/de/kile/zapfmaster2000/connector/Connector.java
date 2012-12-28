package de.kile.zapfmaster2000.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import de.kile.zapfmaster2000.connector.messages.LoginMessage;
import de.kile.zapfmaster2000.connector.messages.Message;
import de.kile.zapfmaster2000.connector.messages.RfidMessage;
import de.kile.zapfmaster2000.connector.messages.TicksMessage;
import de.kile.zapfmaster2000.connector.serial.SerialCommunicator;
import de.kile.zapfmaster2000.connector.serial.SerialConstants;
import de.kile.zapfmaster2000.connector.web.WebCommunicator;

/**
 * Main class of draftkitConnector Makes the connection between the draftkitAVR
 * and the Zapfmaster2000RESTful service
 * 
 * 
 * @author wittekind
 * 
 */
public class Connector implements Runnable, Observer {

	// connects to the draftkitAVR component
	SerialCommunicator serial;
	// connects to the Zapfmaster2000RESTful service
	WebCommunicator web;

	// default tick counting interval in ms
	int defaultInterval = 250;
	
	// for how long is the last rfid tag reception valid
	int idInterval = 1000;
	
	// for how long a failed login will be signaled
	int errorInterval = 3000;

	// default serial port
	String serialPort = "COM9";
	
	// last time of tag id reception
	long lastIdTime = 0;
	
	// last tag id
	long tagId = 0;
	
	// last login status
	byte loginStatus = SerialConstants.STATUSNONE;

	@Override
	public void run() {

		boolean running = true;

		showOptions();

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(is);
		
		// keeps last tagId in order to compare to tagId
		long lastTagId = 0;
		
		// last time at which login was performed
		long lastLoginTime = 0;

		while (running) {
			try {
				String command = in.readLine();
				String[] segments = command.split(" ");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			// check for login
			if (tagId == lastTagId) {
				// check status
				if (loginStatus == SerialConstants.STATUSOK) {
					// login was ok
					// check how old the last tagId reception is
					if ((System.currentTimeMillis()-lastIdTime) > idInterval) {
						// age has surpassed limit
						// send logout message
						loginStatus = SerialConstants.STATUSNONE;
						LoginMessage message = new LoginMessage(loginStatus);
						serial.sendMessage(message);
					}
				} else if (loginStatus == SerialConstants.STATUSERROR) {
					// login had failed
					// check how much time has passed since failed login
					if ((System.currentTimeMillis()-lastLoginTime) > errorInterval) {
						// time has surpassed limit
						// send logout message, return status to none
						loginStatus = SerialConstants.STATUSNONE;
						LoginMessage message = new LoginMessage(loginStatus);
						serial.sendMessage(message);
					}
				}
			} else {
				// get login response 
				int response = web.performLogin(tagId);
				
				LoginMessage message;
				
				// check whether login was successful
				if (response == 200) {
					// was successful
					loginStatus = SerialConstants.STATUSOK;
					message = new LoginMessage(loginStatus);
				} else {
					// was unsuccessful
					loginStatus = SerialConstants.STATUSERROR;
					message = new LoginMessage(loginStatus);
				}
				// send message with login status to draftkitAVR
				serial.sendMessage(message);
				// save tagId
				lastTagId = tagId;
				// save time of this login
				lastLoginTime = System.currentTimeMillis();
			}
		}

	}

	@Override
	public void update(Observable origin, Object message) {
		// cast object
		Message nMessage = (Message) message;
		// initialize response
		int response = 0;
		if (nMessage != null) {
			// message contains rfid tag id
			if (nMessage.getMessageType() == Message.RFIDMESSAGE) {
				// cast to rfid message type
				RfidMessage rMessage = (RfidMessage) nMessage;
				System.out.println("login with id: " + rMessage.getRfidTagId());
				// set time of id renewal
				lastIdTime = System.currentTimeMillis();
			} else
			// message contains ticks
			if (nMessage.getMessageType() == Message.TICKSMESSAGE) {
				// cast to ticks message type
				TicksMessage tMessage = (TicksMessage) nMessage;
				System.out.println("ticks: " + tMessage.getTicks());
				// get response status
				response = web.sendTicks(tMessage.getTicks());
				// deal with response
				if (response != 0) {
					
				}
			}
		}

		// deal with response

	}

	/**
	 * print available user options to console
	 */
	public void showOptions() {
		System.out.println("Commands: ");
		System.out.println("\tstart connector: start");
		System.out.println("\tset a new tick count interval ["
				+ defaultInterval + "]: interval <int>");
		System.out.println("\tset a new serial port [" + serialPort
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
