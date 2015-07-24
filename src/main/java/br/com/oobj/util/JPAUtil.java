package br.com.oobj.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static final String PERSISTENCE_UNIT = "JasperPU";
	private static EntityManagerFactory FACTORY;

	static {
		FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	public static EntityManager createEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return FACTORY;
	}
}
