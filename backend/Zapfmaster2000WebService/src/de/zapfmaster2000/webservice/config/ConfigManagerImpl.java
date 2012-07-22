package de.zapfmaster2000.webservice.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.Assert;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.Config;

public class ConfigManagerImpl implements ConfigManager {

	private static final Logger LOG = Logger.getLogger(ConfigManagerImpl.class);

	private final Map<String, String> fMap = new HashMap<String, String>();

	public ConfigManagerImpl() {
		registerConfigs();
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
			List<?> configs = session.createQuery(
					"select c from Config as c where c.key = '" + pKey + "'")
					.list();

			if (configs.size() == 1) {
				String value = ((Config) configs.get(0)).getValue();
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

	private void registerConfigs() {
		for (ConfigDescriptor descr : ConfigRegistrator.getDescriptors()) {
			String value = get(descr.getName());
			if (value == null) {
				createConfig(descr);
			}
		}
	}

	private void createConfig(ConfigDescriptor pConfig) {
		Assert.isNotNull(pConfig);
		LOG.debug("Creating config: " + pConfig.getName() + " - "
				+ pConfig.getDefaultValue());

		// update Db
		SessionFactory factory = SessionFactoryUtil.getInstance();
		Session session = factory.openSession();

		Config dbConfig = new Config();
		dbConfig.setKey(pConfig.getName());
		dbConfig.setValue(pConfig.getDefaultValue());

		Transaction transaction = session.beginTransaction();
		session.save(dbConfig);
		transaction.commit();

		session.close();
	}

}
