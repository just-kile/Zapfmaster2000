package de.kile.zapfmaster2000.rest.core.push;

import org.jboss.resteasy.spi.AsynchronousResponse;

public interface PushService {

	public void addNewsRequest(AsynchronousResponse pResponse);

}
