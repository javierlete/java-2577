package com.amazonia2.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoSqlite<T> {
	private String url;
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver", e);
		}
	}
	
	public DaoSqlite(String url) {
		this.url = url;
	}
	
	protected Connection obtenerConexion() {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido establecer la conexión a la base de datos", e);
		}
	}
	
	protected void ejecutarCambio(PreparedStatement pst) throws SQLException {
		int numeroRegistrosModificados = pst.executeUpdate();

		if (numeroRegistrosModificados != 1) {
			throw new AccesoDatosException(
					"Número de registros modificados distinto de uno: " + numeroRegistrosModificados);
		}
	}
	
	protected abstract T filaAObjeto(ResultSet rs) throws SQLException;

	protected abstract void objetoAFila(T objeto, PreparedStatement pst) throws SQLException;
}
