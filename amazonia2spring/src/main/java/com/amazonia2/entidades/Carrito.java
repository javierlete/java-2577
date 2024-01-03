package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Component
@SessionScope

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;

	@Builder.Default
	@Transient
	private Map<Long, Producto> productos = new HashMap<>();

	@OneToMany
	public Set<Producto> getProductos() {
		return new HashSet<>(productos.values());
	}
	
	public void setProductos(Set<Producto> productos) {
		this.productos.clear();
		
		productos.stream().forEach(p -> this.productos.put(p.getId(), p));
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
