package de.kile.zapfmaster2000.connector.serial;

import java.io.UnsupportedEncodingException;

import de.kile.zapfmaster2000.connector.messages.IntervalMessage;
import de.kile.zapfmaster2000.connector.messages.LoginMessage;
import de.kile.zapfmaster2000.connector.messages.Message;

/**
 * converts a SerialMessage into a byte array meant for transmission
 * 
 * @author wittekind
 * 
 */
public class SerialEncoder {

	/**
	 * converts a message meant to be send to the draftkitAVR from message to a 'ready-to-send' byte array
	 * @param message
	 * @return
	 */
	public static byte[] encodeMessage(Message message) {
		byte[] data = null;

		// determine message type
		if (message.getMessageType() == Message.LOGINMESSAGE) {
			// initialize data array
			data = new byte[1];
			// set first byte to login message symbol
			data[0] = (byte) SerialConstants.LOGINSYMBOL;
			// cast message to login message
			LoginMessage lMessage = (LoginMessage) message;
			// set second byte to login status symbol from message
			//data[1] = (byte) lMessage.getLoginStatus();
			// set third byte to end symbol
			//data[2] = (byte) SerialConstants.ENDSYMBOL;

		} else if (message.getMessageType() == Message.INTERVALMESSAGE) {

			// cast message to interval message
			IntervalMessage iMessage = (IntervalMessage) message;
			// get interval from message
			int interval = iMessage.getInterval();
			// convert interval to string
			String strInterval = Integer.toString(interval);
			// convert to byte array
			byte[] bytesInterval = null;
			try {
				bytesInterval = strInterval.getBytes("US-ASCII");
			} catch (UnsupportedEncodingException e) {
				// TODO add logger
				e.printStackTrace();
			}
			if (bytesInterval != null) {
				// length of byte array of interval string
				int intervalLength = bytesInterval.length;
				// initialize data array
				data = new byte[intervalLength+2];
				// set first byte to interval message symbol
				data[0] = (byte) SerialConstants.INTERVALSYMBOL;
				// fill following bytes with bytes from interval character array
				for (int i = 0; i < intervalLength; i++) {
					data[i+1] = bytesInterval[i];
				}
				// set last data byte to end symbol
				data[intervalLength+1] = SerialConstants.ENDSYMBOL;
			}
		}

		return data;
	}

}
