package com.amazonia2.accesodatos;

import com.amazonia2.entidades.Usuario;

public class DaoUsuarioJpa extends DaoJpa implements DaoUsuario {

	public DaoUsuarioJpa(String ignorado1, String ignorado2, String ignorado3) {
		super(ignorado1, ignorado2, ignorado3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Usuario> obtenerTodos() {
		return (Iterable<Usuario>) transaccion(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return (Usuario) transaccion(em -> em.find(Usuario.class, id));
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return (Usuario) transaccion(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return (Usuario) transaccion(em -> em.merge(usuario));
	}

	@Override
	public void borrar(Long id) {
		transaccion(em -> {
			em.remove(em.find(Usuario.class, id));
			// em.createNativeQuery("DELETE FROM productos WHERE id = ?1").setParameter(1,
			// id);
			return null;
		});
	}

	@Override
	public long cuantosHay() {
		return (long) transaccion(em -> em.createQuery("select count(u) from Usuario u").getSingleResult());
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return (Usuario) transaccion(em -> em.createQuery("from Usuario u where u.email = ?1", Usuario.class)
				.setParameter(1, email).getResultStream().findFirst().orElse(null));
	}

}
