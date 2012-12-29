package de.kile.zapfmaster2000.connector;

import java.util.Observable;
import java.util.Observer;

import de.kile.zapfmaster2000.connector.messages.IntervalMessage;
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
	// manages the user input
	ConsoleInput console;

	// default tick counting interval in ms
	int defaultInterval = 250;

	// for how long is the last rfid tag reception valid
	int idInterval = 1000;

	// for how long a failed login will be signaled
	int errorInterval = 2000;

	// default serial port
	String serialPort = "COM9";

	// last time of tag id reception
	long lastIdTime = 0;

	// latest rfid tag id
	long curTagId = 0;

	// last login status
	char loginStatus = SerialConstants.STATUSNONE;

	public Connector() {

		// initialize web connection
		web = new WebCommunicator();

	}

	@Override
	public void run() {

		// keeps last tagId in order to compare to tagId
		long lastTagId = 0;

		// last time at which login was performed
		long lastLoginTime = 0;

		// main loop
		while (true) {
			// idle id is 0, see if there's a new id 
			if (curTagId != 0) {
				// check for login
				if (curTagId == lastTagId) {
					// check status
					if (loginStatus == SerialConstants.STATUSOK) {
						// login was ok
						// check how old the last tagId reception is
						if ((System.currentTimeMillis() - lastIdTime) > idInterval) {
							// age has surpassed limit
							// send logout message
							loginStatus = SerialConstants.STATUSNONE;
							LoginMessage message = new LoginMessage(loginStatus);
							serial.sendMessage(message);
							System.out.println("status: none");
							// reset tagIds
							curTagId = 0;
							lastTagId = 0;
						}
					} else if (loginStatus == SerialConstants.STATUSERROR) {
						// login had failed
						// check how much time has passed since failed login
						if ((System.currentTimeMillis() - lastLoginTime) > errorInterval) {
							// time has surpassed limit
							// send logout message, return status to none
							loginStatus = SerialConstants.STATUSNONE;
							LoginMessage message = new LoginMessage(loginStatus);
							serial.sendMessage(message);
							System.out.println("status: none");
							// reset tagIds
							curTagId = 0;
							lastTagId = 0;
						}
					}
				} else {
					// get login response
					int response = web.performLogin(curTagId);

					LoginMessage message = null;

					// check whether login was successful
					if (response == 200) {
						// was successful
						loginStatus = SerialConstants.STATUSOK;
						message = new LoginMessage(loginStatus);
						System.out.println("Login ok");
					} else {
						// was unsuccessful
						loginStatus = SerialConstants.STATUSERROR;
						message = new LoginMessage(loginStatus);
						System.out.println("Login error");
					}
					// send message with login status to draftkitAVR
					serial.sendMessage(message);
					// save tagId
					lastTagId = curTagId;
					// save time of this login
					lastLoginTime = System.currentTimeMillis();
				}
			}
		}
	}

	/**
	 * sends the defaultInterval as new tick interval to the draftkitAVR
	 */
	public void sendNewInterval() {
		if (serial != null) {
			// create message containing the new tick interval
			IntervalMessage message = new IntervalMessage(defaultInterval);
			// send message to draftkitAVR
			serial.sendMessage(message);
		}
	}

	/**
	 * starts the serial connection to the draftkitAVR
	 */
	public void start() {
		// restart serial communicator in case there is an old one
		if (serial != null)
			restart();
		else {
			// create new serial communicator
			serial = new SerialCommunicator();
			// add this class as observer for incoming
			serial.addObserver(this);
			// connect to serial port
			serial.connect(serialPort);
			System.out.println("Connector is now running");
		}
	}

	private void restart() {
		serial.connect(serialPort);
		System.out.println("Connector is reconnected");
	}

	@Override
	public void update(Observable origin, Object message) {
		// cast object
		Message nMessage = (Message) message;
		if (nMessage != null) {
			// message contains rfid tag id
			if (nMessage.getMessageType() == Message.RFIDMESSAGE) {
				// cast to rfid message type
				RfidMessage rMessage = (RfidMessage) nMessage;
				long newTagId = rMessage.getRfidTagId();
				System.out.println("rfid tag id: " + newTagId);
				// only update global tag id, if there's a new card on the
				// reader
				if (newTagId != curTagId)
					curTagId = newTagId;
				// set time of id renewal
				lastIdTime = System.currentTimeMillis();
			} else
			// message contains ticks
			if (nMessage.getMessageType() == Message.TICKSMESSAGE) {
				// cast to ticks message type
				TicksMessage tMessage = (TicksMessage) nMessage;
				System.out.println("ticks: " + tMessage.getTicks());
				// send ticks
				web.sendTicks(tMessage.getTicks());
			}
		}
	}

	public void setDefaultInterval(int newInterval) {
		defaultInterval = newInterval;
	}

	public int getDefaultInterval() {
		return defaultInterval;
	}

	public void setSerialPort(String newSerialPort) {
		serialPort = newSerialPort;
	}

	public String getSerialPort() {
		return serialPort;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connector connector = new Connector();
		Thread tConnector = new Thread(connector);
		ConsoleInput console = new ConsoleInput(connector);
		Thread tConsole = new Thread(console);
		tConnector.start();
		tConsole.start();
	}
}
