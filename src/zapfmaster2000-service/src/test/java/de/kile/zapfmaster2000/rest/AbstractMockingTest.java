package de.kile.zapfmaster2000.rest;

import org.junit.After;
import org.junit.Before;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.auth.AuthService;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.configuration.ConfigurationService;
import de.kile.zapfmaster2000.rest.core.transaction.TransactionService;
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

	public void mockTransactionService(TransactionService pTransactionService) {
		mockery.mockTransactionService(pTransactionService);
	}

	public void mockConfigurationService(ConfigurationService pConfigurationService) {
		mockery.mockConfigurationService(pConfigurationService);
	}

	public void mockBoxService(BoxService pBoxService) {
		mockery.mockBoxService(pBoxService);
	}
	
	public void mockAuthService(AuthService pAuthService) {
		mockery.mockAuthService(pAuthService);
	}

}
