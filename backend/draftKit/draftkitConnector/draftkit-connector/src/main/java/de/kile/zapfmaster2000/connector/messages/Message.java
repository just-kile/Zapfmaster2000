package de.kile.zapfmaster2000.connector.messages;

/**
 * Abstract container for message, that are received by or send to the SerialCommunicator
 * 
 * @author wittekind
 *
 */
public abstract class Message {
	
	public static String RFIDMESSAGE = "rfid";
	public static String TICKSMESSAGE = "ticks";
	public static String LOGINMESSAGE = "login";
	public static String INTERVALMESSAGE = "interval";
	
	protected String messageType;
	
	public abstract String getMessageType();
	
}
