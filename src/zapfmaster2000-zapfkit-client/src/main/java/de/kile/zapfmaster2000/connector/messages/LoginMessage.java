package de.kile.zapfmaster2000.connector.messages;


public class LoginMessage extends Message {

	char status;
	
	/**
	 * sets the message type to login and sets the login status field
	 * @param newStatus - login status according to SerialConstants
	 */
	public LoginMessage(char newStatus) {
		messageType = Message.LOGINMESSAGE;
		status = newStatus;
	}
	
	/**
	 * 
	 * @return - login status (according to SerialConstants)
	 */
	public char getLoginStatus() {
		return status;
	}
	
	@Override
	public int getMessageType() {
		return messageType;
	}

}
