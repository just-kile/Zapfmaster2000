package de.beeromat.internal.core.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.beeromat.core.config.ConfigManager;
import de.beeromat.core.extension.config.Config;
import de.beeromat.core.extension.config.ConfigExtension;
import de.beeromat.core.extension.config.ConfigExtensionResolver;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;

public class ConfigManagerImpl implements ConfigManager {

	private static final Logger LOG = Logger.getLogger(ConfigManagerImpl.class);

	private final Map<String, String> fMap = new HashMap<String, String>();

	public ConfigManagerImpl() {
		try {
			registerConfigs();
		} catch (CoreException ex) {
			LOG.error("Could not resolve configs", ex);
		}
	}

	@Override
	public String get(String pKey) {
		if (pKey != null) {

			// check cache
			if (fMap.containsKey(pKey)) {
				return fMap.get(pKey);
			}

			Transaction tx = null;
			Session session = SessionFactoryUtil.getInstance()
					.getCurrentSession();
			tx = session.beginTransaction();
			List configs = session.createQuery(
					"select c from Config as c where c.key = '" + pKey + "'").list();

			if (configs.size() == 1) {
				String value = ((de.beeromat.core.model.db.Config) configs
						.get(0)).getValue();
				fMap.put(pKey, value);
				return value;
			}
			tx.commit();
		}

		return null;
	}

	@Override
	public double getDouble(String pKey) {
		String raw = get(pKey);
		if (raw == null) {
			return 0;
		}
		return Double.parseDouble(raw);
	}

	@Override
	public int getInt(String pKey) {
		String raw = get(pKey);
		if (raw == null) {
			return 0;
		}
		return Integer.parseInt(raw);
	}

	private void registerConfigs() throws CoreException {
		ConfigExtensionResolver resolver = new ConfigExtensionResolver();
		ConfigExtension[] extensions = resolver.resolve();
		for (ConfigExtension extension : extensions) {
			for (Config config : extension.getConfigs()) {
				String value = get(config.getName());
				if (value == null) {
					createConfig(config);
				}
			}
		}
	}

	private void createConfig(Config pConfig) {
		Assert.isNotNull(pConfig);
		LOG.debug("Creating config: " + pConfig.getName() + " - "
				+ pConfig.getDefault());

		// update Db
		SessionFactory factory = SessionFactoryUtil.getInstance();
		Session session = factory.openSession();

		de.beeromat.core.model.db.Config dbConfig = new de.beeromat.core.model.db.Config();
		dbConfig.setKey(pConfig.getName());
		dbConfig.setValue(pConfig.getDefault());

		Transaction transaction = session.beginTransaction();
		session.save(dbConfig);
		transaction.commit();

		session.close();
	}

}
