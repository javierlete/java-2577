package com.amazonia2.logicanegocio;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.accesodatos.FabricaDao;
import com.amazonia2.accesodatos.FabricaDaoImpl;
import com.amazonia2.entidades.Producto;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	private static final FabricaDao FABRICA = new FabricaDaoImpl("C:/sqlite/amazonia2.properties");
	private static final DaoProducto DAO_PRODUCTO = FABRICA.obtenerDaoProducto(); 
	
	@Override
	public Iterable<Producto> listadoProductos() {
		return DAO_PRODUCTO.obtenerTodos();
	}

}
