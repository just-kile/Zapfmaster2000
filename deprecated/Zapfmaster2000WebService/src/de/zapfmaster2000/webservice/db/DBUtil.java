package de.zapfmaster2000.webservice.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public final class DBUtil {

	public static List<?> executeQuery(String pQuery) {
		Transaction tx = null;
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery(pQuery);
		List<?> result = query.list();
		tx.commit();
		return result;
	}
	
}
