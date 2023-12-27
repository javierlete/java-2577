package com.amazonia2.logicanegocio;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.repositorios.ProductoRepository;

@Component
class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	public AdminNegocioImpl(ProductoRepository repo) {
		super(repo);
	}

	private static final String PRODUCTO = "producto";

	@Override
	public Producto insertarProducto(Producto producto) {
		try {
			if (producto.getCodigoBarras().equals(producto.getNombre())) {
				throw new LogicaNegocioException("No se admiten nombres iguales que un código de barras");
			}
			return repo.save(producto);
		} catch (DuplicateKeyException e) {
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
		return repo.save(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Iterable<Rol> obtenerTodosLosRoles() {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

}
