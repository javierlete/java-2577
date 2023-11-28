package com.amazonia2.presentacion.backend.listeners;

import java.math.BigDecimal;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Producto.ProductoBuilder;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SesionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		Producto p1 = Producto.builder().id(1L).nombre("Producto1").precio(new BigDecimal("1234.12")).unidades(5).build();
		
		ProductoBuilder builder = Producto.builder();
		
		builder.id(2L);
		builder.nombre("Producto2");
		builder.precio(new BigDecimal("234.12"));
		builder.unidades(7);
		
		Producto p2 = builder.build();

		Carrito carrito = Carrito.builder().id(1L).build();
		
		carrito.addProducto(p1);
		carrito.addProducto(p2);

		se.getSession().setAttribute("carrito", carrito);
	}
}
