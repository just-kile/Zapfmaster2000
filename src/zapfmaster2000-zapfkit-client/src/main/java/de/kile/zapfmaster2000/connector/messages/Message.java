package de.kile.zapfmaster2000.connector.messages;

/**
 * Abstract container for message, that are received by or send to the SerialCommunicator
 * 
 * @author wittekind
 *
 */
public abstract class Message {
	
	public static int RFIDMESSAGE = 1;
	public static int TICKSMESSAGE = 2;
	public static int LOGINMESSAGE = 3;
	public static int INTERVALMESSAGE = 4;
	
	protected int messageType;
	
	public abstract int getMessageType();
	
}
