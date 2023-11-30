package com.amazonia2.accesodatos;

import java.time.LocalDate;

import com.amazonia2.entidades.Producto;

public class DaoProductoJpa extends DaoJpa implements DaoProducto {
	
	public DaoProductoJpa(String ignorado1, String ignorado2, String ignorado3) {
		super(ignorado1, ignorado2, ignorado3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Producto> obtenerTodos() {
		return (Iterable<Producto>) transaccion(em -> em.createQuery("from Producto", Producto.class).getResultList());
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return (Producto) transaccion(em -> em.find(Producto.class, id));
	}

	@Override
	public Producto insertar(Producto producto) {
		return (Producto) transaccion(em -> {
			em.persist(producto);
			return producto;
		});
	}

	@Override
	public Producto modificar(Producto producto) {
		return (Producto) transaccion(em -> em.merge(producto));
	}

	@Override
	public void borrar(Long id) {
		transaccion(em -> {
			em.remove(em.find(Producto.class, id));
			// em.createNativeQuery("DELETE FROM productos WHERE id = ?1").setParameter(1, id);
			return null;
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return (Iterable<Producto>) transaccion(
				em -> em.createQuery("from Producto p where p.nombre like '%?1%'", Producto.class)
						.setParameter(1, nombre).getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Producto> obtenerCaducados() {
		return (Iterable<Producto>) transaccion(em -> em
				.createQuery("from Producto p where p.fechaCaducidad < CURRENT_DATE", Producto.class).getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Producto> obtenerCaducados(LocalDate fecha) {
		return (Iterable<Producto>) transaccion(
				em -> em.createQuery("from Producto p where p.fechaCaducidad < ?1", Producto.class)
						.setParameter(1, fecha).getResultList());
	}

}
