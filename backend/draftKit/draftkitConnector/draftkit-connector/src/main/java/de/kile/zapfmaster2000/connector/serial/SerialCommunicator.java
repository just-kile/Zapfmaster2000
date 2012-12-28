package de.kile.zapfmaster2000.connector.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Observer;
import java.util.TooManyListenersException;

import de.kile.zapfmaster2000.connector.messages.Message;

/**
 * 
 * Provides the serial communication with the draftkitAVR Component.
 * 
 * 
 * @author wittekind
 * 
 */
public class SerialCommunicator {

	// baudrate
	final int baud = 57600;

	// serial port
	String serialPort = "";

	// serial reader
	SerialReader reader;

	// serial writer
	SerialWriter writer;

	// list of observers for the serial port
	ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * 
	 * @param port
	 *            - serial port, the draftkitAVR is attached to
	 */
	public SerialCommunicator() {
	}

	/**
	 * attemps to connect to the given serial port and attaches any existing
	 * observers
	 * 
	 * @param portName
	 * @throws Exception
	 */
	public int connect(String portName) {
		int status = -1;
		CommPortIdentifier portIdentifier = null;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		} catch (NoSuchPortException e) {
			// TODO add logger
			e.printStackTrace();
		}
		if (portIdentifier != null) {
			if (portIdentifier.isCurrentlyOwned()) {
				System.out.println("Error: Port is currently in use");
			} else {
				CommPort commPort = null;
				try {
					commPort = portIdentifier.open(this.getClass().getName(),
							2000);
				} catch (PortInUseException e1) {
					// TODO add logger
					e1.printStackTrace();
				}

				if (commPort != null) {
					if (commPort instanceof SerialPort) {
						SerialPort serialPort = (SerialPort) commPort;
						try {
							serialPort.setSerialPortParams(57600,
									SerialPort.DATABITS_8,
									SerialPort.STOPBITS_1,
									SerialPort.PARITY_NONE);
						} catch (UnsupportedCommOperationException e) {
							// TODO add loger
							e.printStackTrace();
						}

						InputStream in = null;
						OutputStream out = null;
						try {
							in = serialPort.getInputStream();
							out = serialPort.getOutputStream();
						} catch (IOException e) {
							// TODO add logger
							e.printStackTrace();
						}

						if ((in != null) && (out != null)) {
							writer = new SerialWriter(out);

							System.out.println("Writer created");

							// create reader as listener for the serial port
							reader = new SerialReader(in);

							System.out.println("Reader created");
							try {
								serialPort.addEventListener(reader);
							} catch (TooManyListenersException e) {
								// TODO add logger
								e.printStackTrace();
							}
							serialPort.notifyOnDataAvailable(true);

							// attach observers to the reader
							updateObservers();

							status = 1;
						}

					} else {
						System.out
								.println("Error: Only serial ports are handled by this example.");
					}
				}
			}
		}

		return status;
	}

	/**
	 * sends a message to the draftkitAVR via serial port
	 * 
	 * @param message
	 *            - message to be send to the draftkitAVR
	 */
	public void sendMessage(Message message) {
		// encode message
		byte[] data = SerialEncoder.encodeMessage(message);
		// send data over serial port
		if (data != null)
			writer.writeOut(data);
	}

	/**
	 * add an observer to the list of observers, that will be attached to the
	 * serial port
	 * 
	 * @param newObserver
	 */
	public void addObserver(Observer newObserver) {
		observers.add(newObserver);
		if (reader != null)
			reader.addObserver(newObserver);
	}

	public void removeObserver(Observer oldObserver) {
		observers.remove(oldObserver);
		if (reader != null)
			reader.deleteObserver(oldObserver);
	}

	/**
	 * refreshes the list of observers, that are attached to the serial port
	 * reader
	 */
	private void updateObservers() {
		if (reader != null) {
			// clear reader's observer list
			reader.deleteObservers();
			// go through list of observers and attach them to reader
			for (Observer observer : observers) {
				reader.addObserver(observer);
			}
		}
	}
}
