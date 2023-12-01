package com.amazonia2.globales;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.accesodatos.DaoRol;
import com.amazonia2.accesodatos.DaoUsuario;
import com.amazonia2.logicanegocio.AdminNegocio;
import com.amazonia2.logicanegocio.UsuarioNegocio;

public interface Fabrica {
	DaoProducto obtenerDaoProducto();
	DaoUsuario obtenerDaoUsuario();
	DaoRol obtenerDaoRol();

	UsuarioNegocio obtenerUsuarioNegocio();
	AdminNegocio obtenerAdminNegocio();
}
