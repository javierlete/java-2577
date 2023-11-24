package com.amazonia2.logicanegocio;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

import static com.amazonia2.globales.Global.FABRICA;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	
	@Override
	public Iterable<Producto> listadoProductos() {
		return FABRICA.obtenerDaoProducto().obtenerTodos();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return FABRICA.obtenerDaoProducto().obtenerPorId(id);
	}

	@Override
	public Usuario loguear(String email, String password) {
		Usuario usuario = FABRICA.obtenerDaoUsuario().obtenerPorEmail(email);
		
		if(usuario != null && usuario.getPassword().equals(password)) {
			return usuario;
		}
		
		return null;
	}

}
