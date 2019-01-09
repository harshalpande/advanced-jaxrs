package org.harshal.javabrains.jwt.hibernate;

import java.util.List;

import org.harshal.javabrains.jwt.model.KeyModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CRUDOperations {

	private static SessionFactory getSessionFactory() {
		HBConnection hibernateConn = HBConnection.getInstance();
		return hibernateConn.getHibernateSessionFactory();
	}
	
	/**
	 * @return Session object
	 */
	public Session getSession () {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	/**
	 * Fetch Operation on KeyModel table
	 * @param anyString
	 * @return KeyModel Object
	 */
	public KeyModel fetchOperation(String userid) {
		Session session = getSession();
		session.beginTransaction();
		Object object = session.get(KeyModel.class, userid);
		session.getTransaction().commit();
		session.close();
		return object == null ? null : (KeyModel) object;
	}
	
	/**
	 * Search by HQL query, <Key, Value> in where clause
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public KeyModel fetchOperation(String key, Object value) {
		KeyModel model;
		Session session = getSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from KeyModel where " + key + " = :str");
		query.setEntity("str", value);
		
		List list = query.list();
		
		if (list.size() > 0) {
			model = (KeyModel)list.get(0);
		} else {
			model = null;
		}
		session.getTransaction().commit();
		session.close();
		
		return model;
	}
	
	
	/**
	 * Update Operation on KeyModel table
	 * @param keyModel Object - to Update
	 */
	public void updateOperation(KeyModel keyModel) {
		Session session = getSession();
		session.beginTransaction();
		session.update(keyModel);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Deletes only Key and TTL by update operation, doesn't delete entire row
	 * @param user
	 */
	public void deleteKeyOperation(String user) {
		Session session = getSession();
		session.beginTransaction();
		KeyModel model = fetchOperation(user);
		model.setKey(null);
		model.setTtl(null);
		model.setToken(null);
		session.update(model);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
