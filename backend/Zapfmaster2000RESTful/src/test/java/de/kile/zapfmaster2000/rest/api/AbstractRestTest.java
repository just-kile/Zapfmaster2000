package de.kile.zapfmaster2000.rest.api;

import org.junit.After;
import org.junit.Before;

import de.kile.zapfmaster2000.rest.core.TransactionManager;
import de.kile.zapfmaster2000.rest.impl.core.CoreMockery;

/**
 * Base class used to test the rest web service. Allows to mock managers from
 * the zapfmaster 2000 core.
 * 
 * @author THomas Kipar
 */
public abstract class AbstractRestTest {

	private CoreMockery mockery;

	@Before
	public void init() {
		mockery = new CoreMockery();
	}

	@After
	public void resetMocks() {
		mockery.resetMocks();
	}

	protected void mockTransactionManager(TransactionManager pTransactionManager) {
		mockery.mockTransactionManager(pTransactionManager);
	}

}
