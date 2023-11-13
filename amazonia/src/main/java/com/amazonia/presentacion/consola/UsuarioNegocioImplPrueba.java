package com.amazonia.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoMemoria;
import com.amazonia.entidades.Producto;
import com.amazonia.logicanegocio.UsuarioNegocio;
import com.amazonia.logicanegocio.UsuarioNegocioImpl;

public class UsuarioNegocioImplPrueba {
	
	public static void main(String[] args) {
		Logger.getLogger(UsuarioNegocioImpl.class.getName()).setLevel(Level.INFO);
		
		DaoProducto dao = new DaoProductoMemoria();
		
		dao.insertar(new Producto());
		dao.insertar(new Producto("Producto prueba"));
		dao.insertar(new Producto("Otro producto", new BigDecimal("1234.56")));
		dao.insertar(new Producto("Un producto más", new BigDecimal("1234.56"), LocalDate.of(2025, 1, 1)));
		
		UsuarioNegocio negocio = new UsuarioNegocioImpl();
		
		for(Producto p: negocio.listadoProductos()) {
			System.out.println(p);
		}
		
		Producto producto = negocio.datosProducto(2L);
		
		System.out.println(producto);
		
		producto = negocio.datosProducto(10L);
		
		System.out.println(producto);
	}
}
