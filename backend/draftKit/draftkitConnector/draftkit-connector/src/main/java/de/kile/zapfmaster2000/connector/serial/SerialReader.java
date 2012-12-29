package de.kile.zapfmaster2000.connector.serial;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

import de.kile.zapfmaster2000.connector.messages.Message;

/**
 * Handles the input coming from the serial port. A new line character is
 * treated as the end of a block in this example.
 */
public class SerialReader extends Observable implements
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
				buffer[len++] = (byte) data;
				if (data == '\n') {
					break;
				}
			}
			System.out.print(new String(buffer, 0, len)+"\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		// get message from received data
		Message message = SerialDecoder.decodeMessage(buffer);
		
		// notify observers
		if (message != null) {
			setChanged();
			notifyObservers(message);
			clearChanged();
		}
	}
}
