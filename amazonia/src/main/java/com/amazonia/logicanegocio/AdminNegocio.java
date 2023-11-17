package com.amazonia.logicanegocio;

import com.amazonia.entidades.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto crearProducto(Producto producto);
}
