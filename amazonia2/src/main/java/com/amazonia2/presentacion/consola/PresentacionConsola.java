package com.amazonia2.presentacion.consola;

import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.UsuarioNegocio;
import com.amazonia2.logicanegocio.UsuarioNegocioImpl;

public class PresentacionConsola {

	public static void main(String[] args) {
		UsuarioNegocio un = new UsuarioNegocioImpl();
		
		for(Producto p: un.listadoProductos()) {
			System.out.println(p);
		}
	}

}
