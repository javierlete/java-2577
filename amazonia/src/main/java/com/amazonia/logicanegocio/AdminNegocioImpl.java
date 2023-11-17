package com.amazonia.logicanegocio;

import java.util.logging.Logger;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoSqlite;
import com.amazonia.entidades.Producto;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	private static final DaoProducto dao = new DaoProductoSqlite();

	private static final Logger log = Logger.getLogger(UsuarioNegocioImpl.class.getName());

	@Override
	public Producto crearProducto(Producto producto) {
		log.info("Se intenta crear el producto " + producto);
		Producto insertado = dao.insertar(producto);
		log.info("Se ha generado " + insertado);
		
		return insertado;
	}

}
