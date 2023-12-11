package com.amazonia2.logicanegocio;

import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;

@Component
class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	@Override
	public Producto insertarProducto(Producto producto) {
		return daoProducto.insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return daoProducto.modificar(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		daoProducto.borrar(id);
	}

	@Override
	public Iterable<Rol> obtenerTodosLosRoles() {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

}
