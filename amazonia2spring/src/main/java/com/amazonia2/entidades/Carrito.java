package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@SessionScope

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Carrito {
	private Long id;
	private Usuario usuario;

	@Builder.Default
	private Map<Long, Producto> productos = new HashMap<>();

	public Set<Producto> getProductos() {
		return new HashSet<>(productos.values());
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

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(Producto p: productos.values()) {
			total = total.add(p.getTotal());
		}
		
		return total;
	}
}
