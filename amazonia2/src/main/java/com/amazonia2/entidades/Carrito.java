package com.amazonia2.entidades;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Carrito {
	private Long id;
	private Usuario usuario;
	
	@Singular
	private Map<Long, Producto> productos;

	public Set<Producto> getProductos() {
		return new HashSet<Producto>(productos.values());
	}

	public void addProducto(Producto producto) {
		productos.put(producto.getId(), producto);
	}
	
	public void updateUnidades(Long id, Integer unidades) {
		productos.get(id).setUnidades(unidades);
	}
	
	public void removeProducto(Long id) {
		productos.remove(id);
	}
	
	public Producto getProducto(Long id) {
		return productos.get(id);
	}
	
	public int getNumeroProductos() {
		return productos.size();
	}
}
