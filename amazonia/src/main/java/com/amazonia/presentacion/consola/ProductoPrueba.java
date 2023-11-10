package com.amazonia.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia.entidades.Producto;

public class ProductoPrueba {
	public static void main(String[] args) {
		Producto p = new Producto(2L, "Prueba", LocalDate.now(), new BigDecimal("1234.56"));
		
		System.out.println(p);
		
		Producto producto = new Producto(null, "Prueba", null, BigDecimal.TEN);

		System.out.println(producto);
		
		producto.setId(1L);
		producto.setNombre("ñlajsfdñlksdg");
		producto.setFechaCaducidad(LocalDate.of(2025, 11, 9));
		producto.setPrecio(new BigDecimal("1234"));

		System.out.println(producto);

		System.out.println(7 > 3);
		System.out.println(new BigDecimal("7").compareTo(new BigDecimal("3")) > 0);
		
		System.out.println(3 > 10);
		
		BigDecimal bd3 = new BigDecimal("3");
		BigDecimal bd10 = new BigDecimal("10");
		
		System.out.println(bd3.compareTo(bd10)); // -1 <, 0 ==, 1 >
		System.out.println(bd3.compareTo(bd10) > 0);
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		System.out.println(p1);
		System.out.println(p2);
		
		System.out.println(p1 == p2); // false / NO son el mismo objeto
		System.out.println(p1.equals(p2)); // Son iguales
		
		Producto p3 = new Producto(p1);
		
		p3.setNombre("Cambiado");
		
		System.out.println(p1);
		System.out.println(p3);
	}
}