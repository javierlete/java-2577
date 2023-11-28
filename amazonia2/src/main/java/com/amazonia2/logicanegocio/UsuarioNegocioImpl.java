package com.amazonia2.logicanegocio;

import static com.amazonia2.globales.Global.FABRICA;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

import lombok.extern.java.Log;

@Log
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
			log.fine(String.format("El usuario %s se ha logueado", email));
			return usuario;
		}
		
		log.warning(String.format("El usuario %s con la contraseña %s no es válido", email, password));
		
		return null;
	}

}
