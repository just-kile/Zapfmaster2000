package de.kile.zapfmaster2000.connector.messages;

/**
 * Holds the RFID information received from the draftkitAVR so it can be passed within the program
 * 
 * @author wittekind
 *
 */
public class RfidMessage extends Message {

	private long tagId;
	
	/**
	 * sets type to rfid message and sets the received rfid s/n
	 * @param rfidNumber - s/n of the scanned rfid tag
	 */
	public RfidMessage(long rfidTagId) {
		messageType = Message.RFIDMESSAGE;
		tagId = rfidTagId;
	}
	
	/**
	 * returns rfid as the message type
	 */
	@Override
	public String getMessageType() {
		return messageType;
	}
	
	/**
	 *
	 * @return rfid tag number contained in this message
	 */
	public long getRfidTagId() {
		return tagId;
	}

}
