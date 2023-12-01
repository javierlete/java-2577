package com.amazonia2.accesodatos;

import com.amazonia2.entidades.Rol;

public class DaoRolJpa extends DaoJpa implements DaoRol {

	public DaoRolJpa(String ignorado1, String ignorado2, String ignorado3) {
		super(ignorado1, ignorado2, ignorado3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Rol> obtenerTodos() {
		return (Iterable<Rol>) transaccion(em -> em.createQuery("from Rol", Rol.class).getResultList());
	}

	@Override
	public Rol obtenerPorId(Long id) {
		return (Rol) transaccion(em -> em.find(Rol.class, id));
	}

	@Override
	public Rol insertar(Rol rol) {
		return (Rol) transaccion(em -> {
			em.persist(rol);
			return rol;
		});
	}

	@Override
	public Rol modificar(Rol rol) {
		return (Rol) transaccion(em -> em.merge(rol));
	}

	@Override
	public void borrar(Long id) {
		transaccion(em -> {
			em.remove(em.find(Rol.class, id));
			// em.createNativeQuery("DELETE FROM productos WHERE id = ?1").setParameter(1, id);
			return null;
		});
	}

	
}
