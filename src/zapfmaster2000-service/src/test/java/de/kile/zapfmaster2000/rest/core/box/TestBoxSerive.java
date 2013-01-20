package de.kile.zapfmaster2000.rest.core.box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import de.kile.zapfmaster2000.rest.AbstractDatabaseTest;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

public class TestBoxSerive extends AbstractDatabaseTest {

	@Before
	public void createBox() {
		Box box = Zapfmaster2000Factory.eINSTANCE.createBox();
		box.setPassphrase("my-passphrase");

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(box);
		tx.commit();
	}

	@Test
	public void testBoxExists() {
		DrawService mgr = Zapfmaster2000Core.INSTANCE.getBoxService()
				.getDrawService("my-passphrase");

		assertNotNull(mgr);
		assertNotNull(mgr.getBox());
		assertEquals("my-passphrase", mgr.getBox().getPassphrase());

		// truncate the db now and get the draw manager once more. Should return
		// the same one even if the box does not exist any more (caching)
		truncate();
		DrawService mgr2 = Zapfmaster2000Core.INSTANCE.getBoxService()
				.getDrawService("my-passphrase");
		assertTrue(mgr == mgr2); // same instance!
	}

	@Test
	public void testBoxDoesNotExist() {
		try {
			Zapfmaster2000Core.INSTANCE.getBoxService().getDrawService(
					"not-existant");
			fail();
		} catch (IllegalArgumentException ex) {
			// pass
		}
	}

}
