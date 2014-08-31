

package de.justkile.zapfmaster2000.utils.constants;

/**
 * General constants of the platform.
 * 
 * @author PB, Thomas Kipar
 *
 */
public class PlatformConstants {

    private PlatformConstants() {
	}

	public static final String DATE_TIME_FORMAT = "yyyyMMdd-HHmmss";

	public static final String DATE_TIME_MS_FORMAT = "yyyyMMdd-HHmmssSSS";

	public static final int ASYNC_TIMEOUT = 60000;

    private static int MIN_TICKS = 5;

    public static double MIN_DRAW_AMOUNT = 0.05;

    private static int BOX_LOGIN_AUTO_LOGOUT = 2000;

    private static int MIN_LOGIN_DIFF = 1000;

}
