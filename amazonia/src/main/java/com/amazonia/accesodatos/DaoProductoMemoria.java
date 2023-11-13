package com.amazonia.accesodatos;

import java.util.TreeMap;

import com.amazonia.entidades.Producto;

public class DaoProductoMemoria implements DaoProducto {

	private static final TreeMap<Long, Producto> productos = new TreeMap<>();

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
	public Iterable<Producto> obtenerPorNombre(String nombreParcial) {
//		var resultado = new ArrayList<Producto>();
//		
//		for(Producto p: productos.values()) {
//			if(p.getNombre().contains(nombreParcial)) {
//				resultado.add(p);
//			}
//		}
//		
//		return resultado;
		
		return productos.values().stream().filter(p -> p.getNombre().contains(nombreParcial)).toList();
	}

}
