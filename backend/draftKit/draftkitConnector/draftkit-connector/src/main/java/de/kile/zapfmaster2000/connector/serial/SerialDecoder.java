package de.kile.zapfmaster2000.connector.serial;

import de.kile.zapfmaster2000.connector.messages.RfidMessage;
import de.kile.zapfmaster2000.connector.messages.Message;
import de.kile.zapfmaster2000.connector.messages.TicksMessage;

/**
 * decodes a given byte array and creates the proper type of SerialMessage from
 * it
 * 
 * @author wittekind
 * 
 */
public class SerialDecoder {

	public static Message decodeMessage(byte[] data) {
		Message message = null;

		// get char from first byte of message
		char symbol = (char) data[0];

		// check what kind of message was send
		if (symbol == SerialConstants.TICKSSYMBOL) {
			// message contains ticks
			// get the bytes up to the end message
			// these bytes contain the number of ticks
			char[] rawNumber = extract(data, 1, findEnd(data));

			// convert the string number to an integer
			int ticks = Integer.valueOf(String.valueOf(rawNumber));

			// create message about ticks reception for observers
			message = new TicksMessage(ticks);

		} else if (symbol == SerialConstants.RFIDSYMBOL) {
			// message contains the rfid s/n
			// get the bytes up to the end message
			// bytes contain the s/n
			char[] rawNumber = extract(data, 1, findEnd(data));

			// new rfid tag id
			long tagId = 0;

			// shift the bytes from the raw message into the long holding
			// the s/n
			for (int i = 0; i < rawNumber.length; i++) {
				tagId += rawNumber[i];
				tagId <<= 8;
			}

			// create message about rfid tag id reception
			message = new RfidMessage(tagId);

		}

		return message;
	}

	/**
	 * finds the first end symbol within the receiving byte buffer
	 * 
	 * @return - index of first end symbol of byte buffer
	 */
	private static int findEnd(byte[] buffer) {
		for (int i = 1; i < buffer.length; ++i) {
			if (buffer[i] == SerialConstants.ENDSYMBOL) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * extracts a character array from the receiving byte buffer
	 * 
	 * @param begin
	 *            - index at which the extraction starts
	 * @param end
	 *            - index at which the extraction ends
	 * @return - extracted byte buffer
	 */
	private static char[] extract(byte[] buffer, int begin, int end) {
		int length = end - begin;
		char[] chars = new char[length];
		for (int i = 0; i < length; ++i) {
			chars[i] = (char) buffer[i];
		}
		return chars;
	}
}
