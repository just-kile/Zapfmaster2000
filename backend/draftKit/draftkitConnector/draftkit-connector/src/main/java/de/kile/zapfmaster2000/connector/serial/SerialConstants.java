package de.kile.zapfmaster2000.connector.serial;

public final class SerialConstants {

	// constants, describing the serial message formatting
	// rx
	public final static char ENDSYMBOL = '\n';
	public final static char TICKSSYMBOL = 'T';
	public final static char RFIDSYMBOL = 'R';

	// tx
	public final static char LOGINSYMBOL = 'L';
	public final static char INTERVALSYMBOL = 'I';

	// login status
	public final static byte STATUSOK = 1;
	public final static byte STATUSERROR = 2;
	public final static byte STATUSNONE = 3;

}
