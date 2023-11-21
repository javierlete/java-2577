package com.amazonia2.presentacion.consola;

import com.amazonia2.entidades.Producto;
import com.amazonia2.globales.Global;
import com.amazonia2.logicanegocio.UsuarioNegocio;

public class PresentacionConsola {

	public static void main(String[] args) {
		UsuarioNegocio un = Global.FABRICA.obtenerUsuarioNegocio(); 
		
		for(Producto p: un.listadoProductos()) {
			System.out.println(p);
		}
	}

}
