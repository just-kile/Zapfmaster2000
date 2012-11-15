package de.beeromat.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import de.beeromat.core.core.BeeromatCore;
import de.beeromat.internal.core.core.BeeromatCoreImpl;

public class BeeromatCoreActivator extends Plugin {

	private BeeromatCore fCore;

	private static BeeromatCoreActivator fInstance;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out
				.println("Firing up Zapfmaster 2000 - the ultimate drawing experience."
						+ " Since this is such an incredible tool, start up lasts some seconds, so be patient.");

		super.start(context);
		fInstance = this;
		fCore = new BeeromatCoreImpl();

		System.out
				.println("Zapfmaster 2000 is ready for usage. Have a lot of fun.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		fInstance = null;
		super.stop(context);
	}

	public static BeeromatCoreActivator getDefault() {
		return fInstance;
	}

	public BeeromatCore getCore() {
		return fCore;
	}

}
