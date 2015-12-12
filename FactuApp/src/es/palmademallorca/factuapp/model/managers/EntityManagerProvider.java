package es.palmademallorca.factuapp.model.managers;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import es.palmademallorca.factuapp.model.jpa.ProductoJPA;

public class EntityManagerProvider {

	private  EntityManagerFactory entityManagerFactory;
	private  static EntityManager entityManager;
	private static EntityManagerProvider provider;

	private EntityManagerProvider() {
		initEntityManager();
	}

	private void initEntityManager() {
		System.out.println("Constructor");		
		entityManagerFactory = Persistence.createEntityManagerFactory("factuPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static synchronized EntityManagerProvider getProvider() {
		if (provider == null) {
			provider = new EntityManagerProvider();
		}
		return provider;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void closeResources() {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}

		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	public static <T> void printAttributesSize(T param) {
		System.out.println("Parametro:"+param);
		Metamodel metamodel = entityManager.getMetamodel();
		ManagedType<T> type1 = metamodel.managedType((Class<T>) param);
		Set<Attribute<? super T, ?>> attributes2 = type1.getAttributes();
		System.out.println("Cantidad atributos:"+attributes2.size());

	}
}
