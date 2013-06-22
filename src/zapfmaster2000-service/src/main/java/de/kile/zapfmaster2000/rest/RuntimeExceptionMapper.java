package de.kile.zapfmaster2000.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

@Provider
public class RuntimeExceptionMapper implements
		ExceptionMapper<RuntimeException> {

	private static final Logger LOGGER = Logger
			.getLogger(RuntimeException.class);

	@Override
	public Response toResponse(RuntimeException exception) {
		LOGGER.fatal("Caught unhandled exception: " + exception.getMessage(),
				exception);

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();

		if (transaction != null && transaction.isActive()) {
			LOGGER.fatal("Found active transaction. Rolling back the transaction.");
			try {
				transaction.rollback();
				LOGGER.info("Did rollback active transaction.");
			} catch (HibernateException ex) {
				LOGGER.fatal("Could not rollback transaction", ex);
			}
		}

		exception.printStackTrace();
		return Response.serverError().entity(exception.toString()).build();
	}

}
