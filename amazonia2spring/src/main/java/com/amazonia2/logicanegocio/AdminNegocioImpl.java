package com.amazonia2.logicanegocio;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.repositorios.ClienteRepository;
import com.amazonia2.repositorios.FacturaRepository;
import com.amazonia2.repositorios.ProductoRepository;
import com.amazonia2.repositorios.UsuarioRepository;

@Component
class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {

	public AdminNegocioImpl(FacturaRepository repoFactura, ClienteRepository repoCliente, UsuarioRepository repoUsuario,
			ProductoRepository repoProducto, ModelMapper mapper) {
		super(repoFactura, repoCliente, repoUsuario, repoProducto, mapper);
		// TODO Auto-generated constructor stub
	}

	private static final String PRODUCTO = "producto";

	@Override
	public Producto insertarProducto(Producto producto) {
		try {
			if (producto.getCodigoBarras().equals(producto.getNombre())) {
				throw new LogicaNegocioException("No se admiten nombres iguales que un código de barras");
			}
			return repoProducto.save(producto);
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
