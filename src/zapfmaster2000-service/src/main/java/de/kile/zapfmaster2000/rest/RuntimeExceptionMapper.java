package de.kile.zapfmaster2000.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class RuntimeExceptionMapper implements
		ExceptionMapper<RuntimeException> {
	
	private static final Logger LOGGER = Logger.getLogger(RuntimeException.class);

	@Override
	public Response toResponse(RuntimeException exception) {
		LOGGER.fatal("Caught unhandled exception", exception);
		exception.printStackTrace();
		return Response.serverError().entity(exception.toString()).build();
	}

}
