package de.kile.zapfmaster2000.connector.serial;

import de.kile.zapfmaster2000.connector.messages.Message;

/**
 * converts a SerialMessage into a byte array meant for transmission
 * 
 * @author wittekind
 *
 */
public class SerialEncoder {
	
	public byte[] encodeMessage (Message message) {
		byte[] data = null;
		
		// determine message type
		if (message.getMessageType().equals(Message.LOGINMESSAGE)) {
			
		} else if (message.getMessageType().equals(Message.INTERVALMESSAGE)) {
			
		}
		
		return data;
	}
	
}
