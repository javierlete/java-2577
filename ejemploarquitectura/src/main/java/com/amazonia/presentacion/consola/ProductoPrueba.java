package com.amazonia.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia.entidades.Producto;

public class ProductoPrueba {
	public static void main(String[] args) {
		Producto producto = new Producto(null, "Prueba", null, BigDecimal.TEN);

		System.out.println(producto);
		
		producto.setId(1L);
		producto.setNombre("ñlajsfdñlksdg");
		producto.setFechaCaducidad(LocalDate.of(2023, 11, 9));
		producto.setPrecio(new BigDecimal("1234"));

		System.out.println(producto);

		System.out.println(7 > 3);
		System.out.println(new BigDecimal("7").compareTo(new BigDecimal("3")) > 0);
		
		System.out.println(3 > 10);
		
		BigDecimal bd3 = new BigDecimal("3");
		BigDecimal bd10 = new BigDecimal("10");
		
		System.out.println(bd3.compareTo(bd10)); // -1 <, 0 ==, 1 >
		System.out.println(bd3.compareTo(bd10) > 0);
		
	}
}
