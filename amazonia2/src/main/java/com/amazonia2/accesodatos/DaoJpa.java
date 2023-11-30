package com.amazonia2.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


abstract class DaoJpa {
	protected DaoJpa(String ignorado1, String ignorado2, String ignorado3) {
	}

	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("com.amazonia2.entidades");

	protected Object transaccion(Function<EntityManager, Object> work) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Object resultado;

		try {
			transaction.begin();
			resultado = work.apply(entityManager);
			transaction.commit();

			return resultado;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new AccesoDatosException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
}
