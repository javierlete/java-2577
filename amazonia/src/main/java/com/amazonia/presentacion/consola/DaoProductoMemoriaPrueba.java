package com.amazonia.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoMemoria;
import com.amazonia.entidades.Producto;

public class DaoProductoMemoriaPrueba {

	public static void main(String[] args) {
		DaoProducto dao = new DaoProductoMemoria();
		
		dao.insertar(new Producto());
		dao.insertar(new Producto("Producto prueba"));
		dao.insertar(new Producto("Otro producto", new BigDecimal("1234.56")));
		dao.insertar(new Producto("Un producto más", new BigDecimal("1234.56"), LocalDate.of(2025, 1, 1)));

		System.out.println("Listado inicial después de inserciones");
		
		for(Producto p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		System.out.println();
		
		Producto producto = dao.obtenerPorId(3L);
		
		producto.setNombre("Modificado");
		
		dao.modificar(producto);
		
		producto = new Producto(1L, "Modificado desde cero", new BigDecimal("1234"), null);
		
		dao.modificar(producto);
		
		dao.borrar(2L);
		
		System.out.println("\nListado después de modificaciones");
		
		for(Producto p: dao.obtenerTodos()) {
			System.out.println(p);
		}
		
		System.out.println("Búsqueda por 'odif'");
		
		for(Producto p: dao.obtenerPorNombre("odif")) {
			System.out.println(p);
		}
	}

}
