package com.amazonia2.accesodatos;

import com.amazonia2.entidades.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
}
