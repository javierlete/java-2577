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
			if (producto.getCodigoBarras().equals(producto.getNombre())) {
				throw new LogicaNegocioException("No se admiten nombres iguales que un código de barras");
			}
			return daoProducto.insertar(producto);
		} catch (DuplicateKeyException e) {
			String dato = e.getMessage().split("'")[1];

			if (dato.equals(producto.getCodigoBarras())) {
				throw new ClaveDuplicadaException("el código de barras está duplicado", "producto", "codigoBarras", e);
			} else if (dato.equals(producto.getNombre())) {
				throw new ClaveDuplicadaException("ese nombre ya existe en la base de datos", "producto", "nombre", e);
			} else {
				throw new ClaveDuplicadaException("hay un campo duplicado", "producto", null, e);
			}
		} catch (LogicaNegocioException e) {
			throw e;
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
