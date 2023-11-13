package com.amazonia.logicanegocio;

import com.amazonia.entidades.Producto;

public interface UsuarioNegocio {
	Iterable<Producto> listadoProductos();
	Producto datosProducto(Long id);
}
