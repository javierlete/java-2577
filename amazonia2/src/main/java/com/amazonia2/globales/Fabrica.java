package com.amazonia2.globales;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.logicanegocio.UsuarioNegocio;

public interface Fabrica {
	DaoProducto obtenerDaoProducto();
	UsuarioNegocio obtenerUsuarioNegocio();
}
