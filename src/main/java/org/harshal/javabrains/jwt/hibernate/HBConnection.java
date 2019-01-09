package org.harshal.javabrains.jwt.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HBConnection {

	private volatile static HBConnection INSTANCE = getInstance();

	private HBConnection() {
		// private constructor
	}

	public static HBConnection getInstance() {
		if (INSTANCE == null) {
			// To make thread safe
			synchronized (HBConnection.class) {
				// check again as multiple threads
				// can reach above step
				if (INSTANCE == null)
					INSTANCE = new HBConnection();
			}
		}
		return INSTANCE;
	}
	
	public SessionFactory getHibernateSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	    return configuration.buildSessionFactory(serviceRegistry);
		
		//deprecated method
		/*return new Configuration().configure().buildSessionFactory();*/
	}

}
