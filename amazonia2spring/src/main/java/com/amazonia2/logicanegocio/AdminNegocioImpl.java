package com.amazonia2.logicanegocio;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.repositorios.ProductoRepository;

@Service
class AdminNegocioImpl // extends UsuarioNegocioImpl
		implements AdminNegocio {

	private ProductoRepository repoProducto;

	public AdminNegocioImpl(ProductoRepository repoProducto) {
		this.repoProducto = repoProducto;
	}

	private static final String PRODUCTO = "producto";

	@Override
	public Producto insertarProducto(Producto producto) {
		try {
			if (producto.getCodigoBarras().equals(producto.getNombre())) {
				throw new LogicaNegocioException("No se admiten nombres iguales que un código de barras");
			}
			return repoProducto.save(producto);
		} catch (DataIntegrityViolationException e) {
			String dato = e.getMessage().split("'")[1];

			if (dato.equals(producto.getCodigoBarras())) {
				throw new ClaveDuplicadaException("el código de barras está duplicado", PRODUCTO, "codigoBarras", e);
			} else if (dato.equals(producto.getNombre())) {
				throw new ClaveDuplicadaException("ese nombre ya existe en la base de datos", PRODUCTO, "nombre", e);
			} else {
				throw new ClaveDuplicadaException("hay un campo duplicado", PRODUCTO, null, e);
			}
		} catch (LogicaNegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new LogicaNegocioException("Error no esperado al insertar");
		}
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return repoProducto.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		repoProducto.deleteById(id);
	}

	@Override
	public Iterable<Rol> obtenerTodosLosRoles() {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

}
