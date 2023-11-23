package com.amazonia2.logicanegocio;

import com.amazonia2.entidades.Producto;
import com.amazonia2.globales.Global;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	
	@Override
	public Producto insertarProducto(Producto producto) {
		return Global.FABRICA.obtenerDaoProducto().insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return Global.FABRICA.obtenerDaoProducto().modificar(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		Global.FABRICA.obtenerDaoProducto().borrar(id);		
	}
	
}
