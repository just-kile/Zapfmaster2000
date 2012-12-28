package de.kile.zapfmaster2000.connector.messages;

/**
 * Holds the ticks information received from the draftkitAVR so it can be passed within the program
 * 
 * @author wittekind
 *
 */
public class TicksMessage extends Message {

	private int ticks;
	
	/**
	 * sets the message type to ticks and sets the amount of ticks received with this message
	 * @param ticks - ticks received from draftkitAVR
	 */
	public TicksMessage (int ticks) {
		messageType = Message.TICKSMESSAGE;
		this.ticks = ticks;
	}
	
	/**
	 * 
	 * @return - ticks received from draftkitAVR
	 */
	public int getTicks() {
		return ticks;
	}
	
	@Override
	public int getMessageType() {
		return messageType;
	}

}
