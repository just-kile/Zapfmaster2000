package de.kile.zapfmaster2000.rest.impl.core.transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;

public class SessionReconfigurator {

	public static void reconfigure() {
		TransactionServiceImpl service = (TransactionServiceImpl) Zapfmaster2000Core.INSTANCE
				.getTransactionService();
		service.reconfigure();
	}

}
