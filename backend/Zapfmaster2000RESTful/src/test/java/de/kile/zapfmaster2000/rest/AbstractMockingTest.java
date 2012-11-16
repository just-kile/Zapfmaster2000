package de.kile.zapfmaster2000.rest;

import org.junit.After;
import org.junit.Before;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxManager;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationManager;
import de.kile.zapfmaster2000.rest.impl.core.CoreMockery;

/**
 * Base class used for test that mock stuff out of the
 * {@link Zapfmaster2000Core}.
 * 
 * @author THomas Kipar
 */
public abstract class AbstractMockingTest extends AbstractDatabaseTest {

	private CoreMockery mockery;

	@Before
	public void init() {
		mockery = new CoreMockery();
	}

	@After
	public void resetMocks() {
		mockery.resetMocks();
	}

	public void mockTransactionManager(TransactionManager pTransactionManager) {
		mockery.mockTransactionManager(pTransactionManager);
	}

	public void mockConfigurationManager(ConfigurationManager pManager) {
		mockery.mockConfigurationManager(pManager);
	}

	public void mockBoxManager(BoxManager pManager) {
		mockery.mockBoxManager(pManager);
	}

}
