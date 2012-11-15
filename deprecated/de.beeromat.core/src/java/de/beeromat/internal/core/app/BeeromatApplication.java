package de.beeromat.internal.core.app;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class BeeromatApplication implements IApplication {
	@Override
	public Object start(IApplicationContext pContext) throws Exception {
		Thread.currentThread().suspend();
		System.out.println("Zapfmaster 2000 says bye.");
		return null;
	}

	@Override
	public void stop() {
	}

}
