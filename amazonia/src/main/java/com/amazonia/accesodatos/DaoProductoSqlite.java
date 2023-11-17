package com.amazonia.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.amazonia.entidades.Producto;

public class DaoProductoSqlite implements DaoProducto {

	private static final String URL = "jdbc:sqlite:/sqlite/amazonia.db";
	private static final String SQL_SELECT = "SELECT id, nombre, precio, fecha_caducidad FROM productos";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id = ?";
	private static final String SQL_SELECT_NOMBRE = SQL_SELECT + " WHERE nombre LIKE ?";
	private static final String SQL_INSERT = "INSERT INTO productos (nombre, precio, fecha_caducidad) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE productos SET nombre=?,precio=?,fecha_caducidad=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se ha encontrado el driver de SQLite");
		}
	}
	
	private Connection obtenerConexion() {
		try {
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido conectar con la base de datos", e);
		}
	}
	
	@Override
	public Iterable<Producto> obtenerTodos() {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			var productos = new ArrayList<Producto>();
			Producto producto;
			LocalDate fecha;
			String sFecha;
			
			while(rs.next()) {
				sFecha = rs.getString("fecha_caducidad");
				fecha = sFecha != null ? LocalDate.parse(sFecha) : null;
				
				producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio"), fecha);
				productos.add(producto);
			}
			
			return productos;
		} catch (SQLException e) {
			throw new RuntimeException("No se han podido obtener todos los registros", e);
		}
	}

	@Override
	public Producto obtenerPorId(Long id) {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);
				) {
			
			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();
			Producto producto = null;
			LocalDate fecha;
			String sFecha;
			
			if(rs.next()) {
				sFecha = rs.getString("fecha_caducidad");
				fecha = sFecha != null ? LocalDate.parse(sFecha) : null;
				
				producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio"), fecha);
			}
			
			return producto;
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido obtener el registro " + id, e);
		}
	}

	@Override
	public Producto insertar(Producto producto) {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);
				) {
			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());
			
			LocalDate fecha = producto.getFechaCaducidad();
			String sFecha = fecha == null ? null : fecha.toString();
			
			pst.setString(3, sFecha);
			
			int numeroRegistrosModificados = pst.executeUpdate();
			
			if(numeroRegistrosModificados != 1) {
				throw new RuntimeException("La inserción ha devuelto " + numeroRegistrosModificados);
			}
			
			return producto;
		} catch (SQLException e) {
			throw new RuntimeException("No se han podido insertar el registro " + producto, e);
		}
	}

	@Override
	public Producto modificar(Producto producto) {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
				) {
			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());
			
			LocalDate fecha = producto.getFechaCaducidad();
			String sFecha = fecha == null ? null : fecha.toString();
			
			pst.setString(3, sFecha);
			pst.setLong(4, producto.getId());
			
			int numeroRegistrosModificados = pst.executeUpdate();
			
			if(numeroRegistrosModificados != 1) {
				throw new RuntimeException("La modificación ha devuelto " + numeroRegistrosModificados);
			}
			
			return producto;
		} catch (SQLException e) {
			throw new RuntimeException("No se han podido modificar el registro " + producto, e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);
				) {
			
			pst.setLong(1, id);
			
			int numeroRegistrosModificados = pst.executeUpdate();
			
			if(numeroRegistrosModificados != 1) {
				throw new RuntimeException("El borrado ha devuelto " + numeroRegistrosModificados);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("No se han podido borrar el registro " + id, e);
		}
	}

	@Override
	public Iterable<Producto> obtenerPorNombre(String nombreParcial) {
		try (Connection con = obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_NOMBRE);
				) {
			pst.setString(1, "%" + nombreParcial + "%");
			
			ResultSet rs = pst.executeQuery();
			var productos = new ArrayList<Producto>();
			Producto producto;
			LocalDate fecha;
			String sFecha;
			
			while(rs.next()) {
				sFecha = rs.getString("fecha_caducidad");
				fecha = sFecha != null ? LocalDate.parse(sFecha) : null;
				
				producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio"), fecha);
				productos.add(producto);
			}
			
			return productos;
		} catch (SQLException e) {
			throw new RuntimeException("No se han podido obtener registros con el nombre parcial " + nombreParcial, e);
		}
	}
	
}
