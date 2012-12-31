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
	int idInterval = 500;

	// default serial port
	String serialPort = "COM9";

	// last time of tag id reception
	long lastIdTime = 0;

	// latest rfid tag id
	long curTagId = 0;

	// last login status
	char loginStatus = SerialConstants.STATUSNONE;

	private final Object SYNC_LOCK = new Object();

	public Connector() {

		// initialize web connection
		web = new WebCommunicator();

	}

	@Override
	public void run() {

		System.out.println("running ");

		// keeps last tagId in order to compare to tagId
		long lastTagId = 0;

		final long polling = 1000;

		long lastRequest = 0;

		// main loop
		while (true) {
			// System.out.println("loop");
			// idle id is 0, see if there's a new id

			
			try {
				long now = System.currentTimeMillis();
				long timeSinceLastId = now - getLastIdTime();
				long currentId = getCurTagId();

				if (timeSinceLastId >= idInterval) {
					// did not receive update for a while -> set status to none
					setCurTagId(0);
					lastTagId = 0;
					System.out.println("status: none");

					// wait -> we are notified when next tag is placed on the
					// reader
					synchronized (this) {
						wait();
					}
				} else {
					if (currentId == lastTagId) {
						// we have got a tag that was processed before
						if (now - lastRequest > polling) {
							// resend the request
							performLogin(currentId);
							lastRequest = now;
						} // else just ignore the rfid tag for now
					} else if (currentId != 0) {
						// new id!
						performLogin(currentId);
						lastRequest = now;
						lastTagId = currentId;
					} else {
						lastTagId = 0;
					}

					// wait at least until we should perform an logout
					long delta = idInterval - timeSinceLastId;
					synchronized (this) {
						wait(delta);
					}
					System.out.println("notified");
				}

			} catch (InterruptedException ex) {
				ex.printStackTrace(); // most likely not going to happen
			}
		}
	}

	/**
	 * Will send a login request for a given rfid tag to the web service.
	 * 
	 * @param pRfidTag
	 *            the tag to perform the login with
	 * @return login result
	 */
	private LoginMessage performLogin(long pRfidTag) {
		int response = web.performLogin(getCurTagId());
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

		return message;
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
				if (newTagId != getCurTagId()) {
					System.out.println("setting tag");
					setCurTagId(newTagId);
				}
				// set time of id renewal
				setLastIdTime(System.currentTimeMillis());
			} else if (nMessage.getMessageType() == Message.TICKSMESSAGE) {
				// message contains ticks
				// cast to ticks message type
				TicksMessage tMessage = (TicksMessage) nMessage;
				System.out.println("ticks: " + tMessage.getTicks());
				// send ticks
				web.sendTicks(tMessage.getTicks());

			}

			synchronized (this) {
				this.notify();
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

	public long getCurTagId() {
		synchronized (SYNC_LOCK) {
			return curTagId;
		}
	}

	public void setCurTagId(long curTagId) {
		synchronized (SYNC_LOCK) {
			this.curTagId = curTagId;
		}
	}

	public long getLastIdTime() {
		synchronized (SYNC_LOCK) {
			return lastIdTime;
		}
	}

	public void setLastIdTime(long lastIdTime) {
		synchronized (SYNC_LOCK) {
			this.lastIdTime = lastIdTime;
		}
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
