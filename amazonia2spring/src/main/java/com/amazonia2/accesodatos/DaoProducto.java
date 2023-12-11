package com.amazonia2.accesodatos;

import java.time.LocalDate;

import com.amazonia2.entidades.Producto;

public interface DaoProducto extends Dao<Producto> {
	Iterable<Producto> obtenerPorNombreParcial(String nombre);
	Iterable<Producto> obtenerCaducados();
	Iterable<Producto> obtenerCaducados(LocalDate fecha);
}
