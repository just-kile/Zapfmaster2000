package de.kile.zapfmaster2000.connector.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import de.kile.zapfmaster2000.connector.messages.IntervalMessage;
import de.kile.zapfmaster2000.connector.messages.LoginMessage;
import de.kile.zapfmaster2000.connector.messages.Message;
import de.kile.zapfmaster2000.connector.messages.RfidMessage;
import de.kile.zapfmaster2000.connector.messages.TicksMessage;
import de.kile.zapfmaster2000.connector.serial.SerialCommunicator;
import de.kile.zapfmaster2000.connector.serial.SerialConstants;

/**
 * used to assert proper function of serial connection
 * 
 * @author wittekind
 * 
 */
public class SerialMock {

	private static void assertParameterCount(String[] pCommand,
			int pExpectedCount) {
		if ((pCommand.length - 1) != pExpectedCount) {
			throw new IllegalArgumentException("Wrong parmaeter count: "
					+ pExpectedCount + " parameter expected.");
		}
	}

	public static void main(String[] args) {
		String comPort = "COM9";

		SerialCommunicator comm = new SerialCommunicator();
		comm.addObserver(new Observer() {
			@Override
			public void update(Observable origin, Object message) {
				// cast object
				Message nMessage = (Message) message;
				if (nMessage != null) {
					if (nMessage.getMessageType() == Message.RFIDMESSAGE) {
						RfidMessage rMessage = (RfidMessage) nMessage;
						System.out.println("login with id: "
								+ rMessage.getRfidTagId());
					} else if (nMessage.getMessageType() == Message.TICKSMESSAGE) {
						TicksMessage tMessage = (TicksMessage) nMessage;
						System.out.println("ticks: " + tMessage.getTicks());
					}
				}
			}
		});

		if (comm.connect(comPort) == 1) {

			System.out.println("Commands: ");
			System.out.println("\tlogin 1|2|3");
			System.out.println("\tinterval <int>");

			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(is);

			while (true) {
				try {
					String command = in.readLine();
					String[] segments = command.split(" ");

					switch (segments[0]) {
					case "login":
						assertParameterCount(segments, 1);
						int status = Integer.parseInt(segments[1]);
						LoginMessage lMessage;
						switch (status) {
						case 1:
							lMessage = new LoginMessage(
									SerialConstants.STATUSOK);
							break;
						case 2:
							lMessage = new LoginMessage(
									SerialConstants.STATUSERROR);
							break;
						case 3:
							lMessage = new LoginMessage(
									SerialConstants.STATUSNONE);
							break;
						default:
							lMessage = new LoginMessage(
									SerialConstants.STATUSNONE);
						}
						comm.sendMessage(lMessage);
						break;
					case "interval":
						assertParameterCount(segments, 1);
						int interval = Integer.parseInt(segments[1]);
						IntervalMessage iMessage = new IntervalMessage(interval);
						comm.sendMessage(iMessage);
						break;
					default:
						System.out.println("unknown command: " + segments[0]);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
