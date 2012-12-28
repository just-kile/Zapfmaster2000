package de.kile.zapfmaster2000.connector.messages;

/**
 * message containing the interval in ms within which the ticks from the flow meter will be counted
 * 
 * @author wittekind
 *
 */
public class IntervalMessage extends Message {

	int interval;
	
	/**
	 * sets the message type to interval and sets the interval field
	 * @param newInterval - flow meter tick count interval in ms
	 */
	public IntervalMessage (int newInterval) {
		messageType = Message.INTERVALMESSAGE;
		interval = newInterval;
	}
	
	public int getInterval () {
		return interval;
	}
	
	@Override
	public int getMessageType() {
		return messageType;
	}

}
