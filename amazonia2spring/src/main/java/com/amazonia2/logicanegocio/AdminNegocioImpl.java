package com.amazonia2.logicanegocio;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;

@Component
class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	@Override
	public Producto insertarProducto(Producto producto) {
		try {
			return daoProducto.insertar(producto);
		} catch (DuplicateKeyException e) {
			throw new ClaveDuplicadaException("el código de barras está duplicado", "producto", "codigoBarras", e);
		} catch (Exception e) {
			throw new LogicaNegocioException("Error no esperado al insertar");
		}
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
