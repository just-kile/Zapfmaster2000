package de.kile.zapfmaster2000.connector.serial;

import java.io.IOException;
import java.io.OutputStream;

/**
 * sends data to the draftkitAVR component via serial port
 */
public class SerialWriter {
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
			out.flush();
		} catch (IOException e) {
			// TODO add logger
			e.printStackTrace();
		}
	}

}
