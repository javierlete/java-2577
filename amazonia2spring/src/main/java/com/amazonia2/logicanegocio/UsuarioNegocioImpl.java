package com.amazonia2.logicanegocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

import lombok.extern.java.Log;

@Log
@Component
@Primary
class UsuarioNegocioImpl implements UsuarioNegocio {

	@Autowired
	protected DaoProducto daoProducto;

	@Override
	public Iterable<Producto> listadoProductos() {
		return daoProducto.obtenerTodos();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return daoProducto.obtenerPorId(id);
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
		return daoProducto.cuantosHay();
	}

	@Override
	public void agregarProductoACarrito(Long id, Carrito carrito) {
		Producto producto = detalleProducto(id);
		agregarProductoACarrito(producto, carrito);
	}

	@Override
	public void agregarProductoACarrito(Producto producto, Carrito carrito) {
		Producto existente = carrito.getProducto(producto.getId());

		if (existente == null) {
			producto.setUnidades(1);
			carrito.addProducto(producto);
		} else {
			existente.setUnidades(existente.getUnidades() + 1);
		}

		log.info("Se ha agregado el producto " + producto + " a un carrito");
	}

}
