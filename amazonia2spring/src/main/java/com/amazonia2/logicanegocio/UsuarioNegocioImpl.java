package com.amazonia2.logicanegocio;

import java.util.Optional;

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

		if (producto == null) {
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

	@Override
	public Carrito quitarProductoDeCarrito(Long id, Carrito carrito) {
		Producto producto = carrito.getProducto(id);

		if (producto == null) {
			throw new LogicaNegocioException("No existe el producto a eliminar");
		} else {
			carrito.removeProducto(id);
		}

		log.info("Se ha quitado el producto " + producto + " de un carrito");

		return carrito;
	}

	@Override
	public Carrito quitarProductoDeCarrito(Producto producto, Carrito carrito) {
		return quitarProductoDeCarrito(producto.getId(), carrito);
	}

	@Override
	public Carrito quitarUnidadDeProductoDeCarrito(Long id, Carrito carrito) {
		Integer unidades = carrito.getProducto(id).getUnidades();

		Optional<Producto> productoAlmacen = repo.findById(id);

		if (productoAlmacen.isEmpty() || unidades == 1) {
			quitarProductoDeCarrito(id, carrito);
			return carrito;
		}
		
		carrito.updateUnidades(id, unidades - 1);

		return carrito;
	}

	@Override
	public Carrito agregarUnidadDeProductoDeCarrito(Long id, Carrito carrito) {
		Integer unidades = carrito.getProducto(id).getUnidades();

		Optional<Producto> productoAlmacen = repo.findById(id);

		if (productoAlmacen.isEmpty()) {
			quitarProductoDeCarrito(id, carrito);
			return carrito;
		}

		if (unidades + 1 <= productoAlmacen.get().getUnidades()) {
			carrito.updateUnidades(id, unidades + 1);
		}
		
		return carrito;
	}
}
