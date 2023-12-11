package com.amazonia2.logicanegocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

@Component
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
	
	
}
