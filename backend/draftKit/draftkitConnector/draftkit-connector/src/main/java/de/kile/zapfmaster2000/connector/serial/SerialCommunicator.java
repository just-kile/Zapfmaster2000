package de.kile.zapfmaster2000.connector.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
	public SerialCommunicator(String port) {
		serialPort = port;
	}

	/**
	 * attemps to connect to the given serial port and attaches any existing
	 * observers
	 * 
	 * @param portName
	 * @throws Exception
	 */
	void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					2000);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();

				writer = new SerialWriter(out);

				// create reader as listener for the serial port
				reader = new SerialReader(in);
				serialPort.addEventListener(reader);
				serialPort.notifyOnDataAvailable(true);

				// attach observers to the reader
				updateObservers();

			} else {
				System.out
						.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	/**
	 * add an observer to the list of observers, that will be attached to the
	 * serial port
	 * 
	 * @param newObserver
	 */
	void addObserver(Observer newObserver) {
		observers.add(newObserver);
		if (reader != null)
			reader.addObserver(newObserver);
	}

	void removeObserver(Observer oldObserver) {
		observers.remove(oldObserver);
		if (reader != null)
			reader.deleteObserver(oldObserver);
	}

	/**
	 * refreshes the list of observers, that are attached to the serial port
	 * reader
	 */
	void updateObservers() {
		if (reader != null) {
			// clear reader's observer list
			reader.deleteObservers();
			// go through list of observers and attach them to reader
			for (Observer observer : observers) {
				reader.addObserver(observer);
			}
		}
	}

	/**
	 * Handles the input coming from the serial port. A new line character is
	 * treated as the end of a block in this example.
	 */
	public static class SerialReader extends Observable implements
			SerialPortEventListener {
		private InputStream in;
		private byte[] buffer = new byte[1024];

		public SerialReader(InputStream in) {
			this.in = in;
		}

		/**
		 * triggered once new data is available on the serial port read the
		 * available data bytes and processes the contained messages notifies
		 * observers with message content, if applicable
		 */
		public void serialEvent(SerialPortEvent arg0) {
			int data;

			try {
				int len = 0;
				while ((data = in.read()) > -1) {
					if (data == '\n') {
						break;
					}
					buffer[len++] = (byte) data;
				}
				System.out.print(new String(buffer, 0, len));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			// get message from received data
			Message message = SerialDecoder.decodeMessage(buffer);
			
			// notify observers
			if (message != null) {
				notifyObservers(message);					
			}
		}
	}

	/**
	 * sends data to the draftkitAVR component via serial port
	 */
	public static class SerialWriter {
		OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		/**
		 * writes
		 * 
		 * @param outBuffer
		 */
		public void writeOut(byte[] outBuffer) {
			try {
				out.write(outBuffer);
			} catch (IOException e) {
				// TODO add logger
				e.printStackTrace();
			}
		}

	}

}
