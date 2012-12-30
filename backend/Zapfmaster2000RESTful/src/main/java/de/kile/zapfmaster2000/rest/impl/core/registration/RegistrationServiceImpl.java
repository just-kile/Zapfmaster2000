package de.kile.zapfmaster2000.rest.impl.core.registration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.registration.RegistrationException;
import de.kile.zapfmaster2000.rest.core.registration.RegistrationService;
import de.kile.zapfmaster2000.rest.core.registration.RegistrationServiceListener;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Sex;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class RegistrationServiceImpl implements RegistrationService {

	/** logger */
	private static final Logger LOG = Logger
			.getLogger(RegistrationServiceImpl.class);

	private final List<RegistrationServiceListener> listeners = new ArrayList<>();

	@Override
	public User registerUser(Account pAccount, String pUserName,
			String pPassword, Sex pSex, int pWeight)
			throws RegistrationException {

		if (pAccount != null && pUserName != null && pPassword != null
				&& pSex != null && pWeight > 0) {

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();

			User user;

			try {
				// check if a user with the given name does already exist
				List<?> result = session
						.createQuery("FROM User u WHERE u.name = :userName")
						.setString("userName", pUserName).list();
				if (!result.isEmpty()) {
					LOG.error("User with name " + pUserName
							+ " does already exist");
					throw new RegistrationException(
							"User with given name already exists");
				}

				// create the new user
				pAccount = (Account) session.load(
						Zapfmaster2000Package.eINSTANCE.getAccount().getName(),
						pAccount.getId());
				user = Zapfmaster2000Factory.eINSTANCE.createUser();
				user.setAccount(pAccount);
				user.setImagePath("images/others/defaultUser.png");
				user.setName(pUserName);
				user.setPassword(pPassword);
				user.setWeight(pWeight);
				user.setSex(pSex);
				session.save(user);

			} finally {
				tx.commit();
			}

			notifyNewUserRegistered(user);
			return user;

		} else {
			throw new RegistrationException("Invalid parameters");
		}

	}

	@Override
	public void addListener(RegistrationServiceListener pListener) {
		if (pListener != null) {
			listeners.add(pListener);
		}
	}

	@Override
	public void removeListener(RegistrationServiceListener pListener) {
		listeners.remove(pListener);
	}
	
	private void notifyNewUserRegistered(User pUser) {
		for (RegistrationServiceListener listener : listeners) {
			listener.onNewUserRegistered(pUser);
		}
	}

}
