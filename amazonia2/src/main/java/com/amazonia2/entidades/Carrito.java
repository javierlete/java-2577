package com.amazonia2.entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Carrito {
	private Long id;
	private Usuario usuario;
	
	private Map<Long, Producto> productos = new HashMap<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Producto> getProductos() {
		return new HashSet<Producto>(productos.values());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productos, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		return Objects.equals(id, other.id) && Objects.equals(productos, other.productos)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", usuario=" + usuario + ", productos=" + productos + "]";
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
