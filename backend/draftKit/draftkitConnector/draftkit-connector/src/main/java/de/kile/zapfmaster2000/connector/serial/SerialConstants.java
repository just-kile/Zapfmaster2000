package de.kile.zapfmaster2000.connector.serial;

public class SerialConstants {

	// constants, describing the serial message formatting
	// rx
	final static char ENDSYMBOL = '\n';
	final static char TICKSSYMBOL = 'T';
	final static char RFIDSYMBOL = 'R';

	// tx
	final static char LOGINSYMBOL = 'L';
	final static char INTERVALSYMBOL = 'I';

	// login status
	final static byte STATUSOK = 1;
	final static byte STATUSERROR = 2;
	final static byte STATUSNONE = 3;

}
