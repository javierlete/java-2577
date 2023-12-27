package com.amazonia2.logicanegocio;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;
import com.amazonia2.repositorios.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;

@Log
@Component
@Primary
class UsuarioNegocioImpl implements UsuarioNegocio {

	protected ProductoRepository repo;
	
	public UsuarioNegocioImpl(ProductoRepository repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<Producto> listadoProductos() {
		return repo.findAll();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Usuario loguear(String email, String password) {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

	@Override
	public long cuantosUsuariosHay() {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

	@Override
	public long cuantosProductosHay() {
		return repo.count();
	}

	@Override
	public Carrito agregarProductoACarrito(Long id, Carrito carrito) {
		Producto producto = detalleProducto(id);
		
		if(producto == null) {
			throw new EntityNotFoundException("No se ha encontrado el producto " + id);
		}
		
		agregarProductoACarrito(producto, carrito);
		
		return carrito;
	}

	@Override
	public Carrito agregarProductoACarrito(Producto producto, Carrito carrito) {
		Producto existente = carrito.getProducto(producto.getId());

		if (existente == null) {
			producto.setUnidades(1);
			carrito.addProducto(producto);
		} else {
			existente.setUnidades(existente.getUnidades() + 1);
		}

		log.info("Se ha agregado el producto " + producto + " a un carrito");
		
		return carrito;
	}

}
