package com.amazonia2.accesodatos;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Producto;

@Component
class DaoProductoJdbc implements DaoProducto {
	private static final String SQL_SELECT = "SELECT id, codigo_barras, nombre, precio, fecha_caducidad, unidades FROM productos";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
	private static final String SQL_SELECT_CUANTOS = "SELECT COUNT(*) FROM productos";
	private static final String SQL_SELECT_CADUCADOS = SQL_SELECT + " WHERE fecha_caducidad < ?";
	private static final String SQL_SELECT_NOMBRE = SQL_SELECT + " WHERE nombre LIKE ?";
//	private static final String SQL_INSERT = "INSERT INTO productos (codigo_barras, nombre, precio, fecha_caducidad, unidades) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE productos SET codigo_barras=?, nombre=?, precio=?, fecha_caducidad=?, unidades=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

	@Autowired
	private JdbcTemplate jdbc;

	private SimpleJdbcInsert insertProducto;

	public DaoProductoJdbc(DataSource dataSource) {
		this.insertProducto = new SimpleJdbcInsert(dataSource).withTableName("productos")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return jdbc.query(SQL_SELECT, new BeanPropertyRowMapper<Producto>(Producto.class));
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return jdbc.queryForObject(SQL_SELECT_ID, new BeanPropertyRowMapper<Producto>(Producto.class), id);
	}

	@Override
	public Producto insertar(Producto producto) {
//		jdbc.update(SQL_INSERT, producto.getCodigoBarras(), producto.getNombre(), producto.getPrecio(), producto.getFechaCaducidad(), producto.getUnidades());
//		return producto;

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(producto);
		Number newId = insertProducto.executeAndReturnKey(parameters);
		producto.setId(newId.longValue());

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		jdbc.update(SQL_UPDATE, producto.getCodigoBarras(), producto.getNombre(), producto.getPrecio(),
				producto.getFechaCaducidad(), producto.getUnidades(), producto.getId());
		return producto;
	}

	@Override
	public void borrar(Long id) {
		jdbc.update(SQL_DELETE, id);
	}

	@Override
	public long cuantosHay() {
		return jdbc.queryForObject(SQL_SELECT_CUANTOS, Long.class);
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return jdbc.query(SQL_SELECT_NOMBRE, new BeanPropertyRowMapper<Producto>(Producto.class), "%" + nombre + "%");
	}

	@Override
	public Iterable<Producto> obtenerCaducados() {
		return obtenerCaducados(LocalDate.now());
	}

	@Override
	public Iterable<Producto> obtenerCaducados(LocalDate fecha) {
		return jdbc.query(SQL_SELECT_CADUCADOS, new BeanPropertyRowMapper<Producto>(Producto.class), fecha);
	}

}
