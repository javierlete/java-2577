package com.amazonia2.logicanegocio;

import com.amazonia2.entidades.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto insertarProducto(Producto producto);
	Producto modificarProducto(Producto producto);
	void borrarProducto(Long id);
}
