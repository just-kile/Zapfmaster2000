package de.kile.zapfmaster2000.rest;

import javax.ws.rs.core.Application;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

/**
 * Zapfmaster2000 Application to run in a servlet container.
 * 
 * @author Thomas Kipar
 */
public class Zapfmaster2000Application extends Application {

	public Zapfmaster2000Application() {
		// instantiate the Zapfmaster2000Core just to perform all required
		// initialization on start up time
		@SuppressWarnings("unused")
		Zapfmaster2000Core core = Zapfmaster2000Core.INSTANCE;		
	}

}
