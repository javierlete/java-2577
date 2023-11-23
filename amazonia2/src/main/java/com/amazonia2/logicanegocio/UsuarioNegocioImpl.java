package com.amazonia2.logicanegocio;

import com.amazonia2.entidades.Producto;
import static com.amazonia2.globales.Global.FABRICA;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	
	@Override
	public Iterable<Producto> listadoProductos() {
		return FABRICA.obtenerDaoProducto().obtenerTodos();
	}

	@Override
	public Producto detalle(Long id) {
		return FABRICA.obtenerDaoProducto().obtenerPorId(id);
	}

}
