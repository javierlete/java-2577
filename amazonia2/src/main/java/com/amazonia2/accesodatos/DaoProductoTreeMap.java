package com.amazonia2.accesodatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import com.amazonia2.entidades.Producto;

public class DaoProductoTreeMap implements DaoProducto {
	private static final TreeMap<Long, Producto> productos = new TreeMap<>();
	
	static {
		productos.put(1L, Producto.builder().id(1L).nombre("Portátil").precio(new BigDecimal("1234.12")).build());
		productos.put(2L, Producto.builder().id(2L).nombre("Monitor").precio(new BigDecimal("123.12")).build());
		productos.put(3L, Producto.builder().id(3L).nombre("Ratón").precio(new BigDecimal("12.12")).build());
	}

	public DaoProductoTreeMap(String url, String user, String pass) {}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.get(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = productos.size() > 0 ? productos.lastKey() + 1L : 1L;
		producto.setId(id);
		productos.put(id, producto);
		
		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		productos.put(producto.getId(), producto);
		
		return producto;
	}

	@Override
	public void borrar(Long id) {
		productos.remove(id);
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return productos.values().stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

	@Override
	public Iterable<Producto> obtenerCaducados() {
		return obtenerCaducados(LocalDate.now());
	}

	@Override
	public Iterable<Producto> obtenerCaducados(LocalDate fecha) {
		return productos.values().stream().filter(p -> p.getFechaCaducidad().isBefore(fecha)).toList();
	}
}
