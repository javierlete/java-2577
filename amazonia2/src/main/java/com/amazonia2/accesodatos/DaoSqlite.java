package com.amazonia2.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Dao genérico con múltiples operaciones para simplificar el acceso a la base de datos SQLite
 * @param <T>
 */
abstract class DaoSqlite<T> {
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

	protected Iterable<T> consultaVarios(String sql) {
		return consultaVarios(sql, null);
	}
	
	@SuppressWarnings("unchecked")
	protected Iterable<T> consultaVarios(String sql, Object dato) {
		return (Iterable<T>) consulta(sql, pst -> {
			try {
				if (dato != null) {
					pst.setObject(1, dato);
				}

				ArrayList<T> resultados;

				try (ResultSet rs = pst.executeQuery()) {
					resultados = new ArrayList<T>();

					T objeto;

					while (rs.next()) {
						objeto = filaAObjeto(rs);

						resultados.add(objeto);
					}

					return resultados;
				}
			} catch (SQLException e) {
				throw new AccesoDatosException("Error en la consulta de un elemento " + dato, e);
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected T consultaUno(String sql, Object dato) {
		return (T) consulta(sql, pst -> {
			try {
				pst.setObject(1, dato);

				T objeto;
				try (ResultSet rs = pst.executeQuery()) {
					objeto = null;

					if (rs.next()) {
						objeto = (T) filaAObjeto(rs);
					}

					return objeto;
				}
			} catch (SQLException e) {
				throw new AccesoDatosException("Error en la consulta de un elemento " + dato, e);
			}
		});
	}

	private Object consulta(String sql, Function<PreparedStatement, Object> proceso) {
		try (Connection con = obtenerConexion(); PreparedStatement pst = con.prepareStatement(sql);) {
			return proceso.apply(pst);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta " + sql, e);
		}
	}

	// consulta("SELECT *...", new Object[] { "asdf", "adsfas" });
//	private Object consulta(String sql, Object[] argumentos) {
//		
//	}
	
	// consulta("SELECT *...", pst -> {...}, "asdf", "asdfasdhg", "alkdsjflasd")
	protected Object consulta(String sql, Function<PreparedStatement, Object> proceso, Object... argumentos) {
		try (Connection con = obtenerConexion(); PreparedStatement pst = con.prepareStatement(sql);) {
			for(int i = 0; i < argumentos.length; i++) {
				pst.setObject(i + 1, argumentos[i]);
			}
			return proceso.apply(pst);
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta " + sql, e);
		}
	}

	protected void cambio(String sql, Long id) {
		consulta(sql, pst -> {
			try {
				pst.setLong(1, id);

				ejecutarCambio(pst);
			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido ejecutar el cambio " + sql, e);
			}

			return null;
		});
	}

	@SuppressWarnings("unchecked")
	protected T cambio(String sql, T objeto) {
		return (T) consulta(sql, pst -> {
			try {
				objetoAFila(objeto, pst);

				ejecutarCambio(pst);

				return objeto;
			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido ejecutar el cambio " + sql, e);
			}
		});
	}

	protected abstract T filaAObjeto(ResultSet rs) throws SQLException;

	protected abstract void objetoAFila(T objeto, PreparedStatement pst) throws SQLException;
}
