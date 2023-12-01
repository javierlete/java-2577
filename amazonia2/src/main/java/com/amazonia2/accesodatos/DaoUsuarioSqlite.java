package com.amazonia2.accesodatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amazonia2.entidades.Rol;
import com.amazonia2.entidades.Usuario;

public class DaoUsuarioSqlite extends DaoSqlite<Usuario> implements DaoUsuario {

	private static final String SQL_SELECT = "SELECT u.id AS id, u.nombre AS nombre, u.email AS email, u.password AS password, r.id AS rol_id, r.nombre AS rol_nombre FROM usuarios AS u JOIN roles AS r ON u.roles_id = r.id";
	private static final String SQL_SELECT_EMAIL = SQL_SELECT + " WHERE u.email = ?";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE u.id = ?";
	private static final String SQL_INSERT = "INSERT INTO usuarios (nombre, email, password, roles_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET nombre=?,email=?,password=?,roles_id=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id=?";

	public DaoUsuarioSqlite(String url, String user, String pass) {
		super(url);
	}

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return consultaVarios(SQL_SELECT);
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return consultaUno(SQL_SELECT_ID, id);
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		usuario.setId(null);
		
		return cambio(SQL_INSERT, usuario);
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return cambio(SQL_UPDATE, usuario);
	}

	@Override
	public void borrar(Long id) {
		cambio(SQL_DELETE, id);
	}

	
	@Override
	public Usuario obtenerPorEmail(String email) {
		return consultaUno(SQL_SELECT_EMAIL, email);
	}

	@Override
	protected Usuario filaAObjeto(ResultSet rs) throws SQLException {
		Rol rol = Rol.builder().id(rs.getLong("rol_id")).nombre(rs.getString("rol_nombre")).build();
		return new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"),
				rol);
	}

	@Override
	protected void objetoAFila(Usuario usuario, PreparedStatement pst) throws SQLException {
		pst.setString(1, usuario.getNombre());
		pst.setString(2, usuario.getEmail());
		pst.setString(3, usuario.getPassword());
		pst.setLong(4, usuario.getRol().getId());
		
		if (usuario.getId() != null) {
			pst.setLong(5, usuario.getId());
		}
	}
}
