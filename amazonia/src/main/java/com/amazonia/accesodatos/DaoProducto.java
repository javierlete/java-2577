package com.amazonia.accesodatos;

import com.amazonia.entidades.Producto;

public interface DaoProducto extends Dao<Producto> {
	Iterable<Producto> obtenerPorNombre(String nombreParcial);
}
