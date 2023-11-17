package com.amazonia.logicanegocio;

import java.util.logging.Logger;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoSqlite;
import com.amazonia.entidades.Producto;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	private static final DaoProducto dao = new DaoProductoSqlite();
	
	private static final Logger log = Logger.getLogger(UsuarioNegocioImpl.class.getName());
	
	@Override
	public Iterable<Producto> listadoProductos() {
		log.info("Se ha pedido un listado de productos");
		Iterable<Producto> productos = dao.obtenerTodos();
		log.info("Se ha obtenido un listado de productos");
		
		return productos;
	}

	@Override
	public Producto datosProducto(Long id) {
		log.info("Se ha pedido el producto cuyo id es " + id);
		
		Producto producto = dao.obtenerPorId(id);
		
		if(producto == null) {
			log.warning("No se ha encontrado el producto id " + id);
		}
		
		log.info("Se ha obtenido el producto " + producto);
		
		return producto;
	}

}
