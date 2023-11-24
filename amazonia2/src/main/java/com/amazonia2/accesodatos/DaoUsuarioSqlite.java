package com.amazonia2.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amazonia2.entidades.Rol;
import com.amazonia2.entidades.Usuario;

public class DaoUsuarioSqlite extends DaoSqlite<Usuario> implements DaoUsuario {

	private static final String SQL_SELECT_EMAIL = "SELECT u.id AS id, u.nombre AS nombre, u.email AS email, u.password AS password, r.id AS rol_id, r.nombre AS rol_nombre FROM usuarios AS u JOIN roles AS r ON u.roles_id = r.id WHERE u.email = ?";

	public DaoUsuarioSqlite(String url, String user, String pass) {
		super(url);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		try (Connection con = obtenerConexion(); PreparedStatement pst = con.prepareStatement(SQL_SELECT_EMAIL);) {
			pst.setString(1, email);

			Usuario usuario;
			try (ResultSet rs = pst.executeQuery()) {
				usuario = null;

				if (rs.next()) {
					usuario = filaAObjeto(rs);
				}

				return usuario;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta del email " + email, e);
		}
	}

	@Override
	protected Usuario filaAObjeto(ResultSet rs) throws SQLException {
		Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"));
		return new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"),
				rol);
	}

	@Override
	protected void objetoAFila(Usuario objeto, PreparedStatement pst) throws SQLException {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

}
