package com.amazonia.presentacion.consola;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoSqlite;
import com.amazonia.entidades.Producto;

public class DaoProductoSqlitePrueba {

	public static void main(String[] args) {
		DaoProducto dao = new DaoProductoSqlite();

		dao.insertar(new Producto("Prueba"));
		
		Producto producto = dao.obtenerPorId(2L);
		producto.setNombre("Modificado");
		dao.modificar(producto);
		
		dao.borrar(5L);
		
		for(Producto p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		for(Producto p: dao.obtenerPorNombre("a")) {
			System.out.println(p);
		}
	}

}
