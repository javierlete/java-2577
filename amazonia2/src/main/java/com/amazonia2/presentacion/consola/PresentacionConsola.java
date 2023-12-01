package com.amazonia2.presentacion.consola;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.globales.Global;

public class PresentacionConsola {

	public static void main(String[] args) {
		for(Producto p: Global.UN.listadoProductos()) {
			System.out.println(p);
		}
		
		for(Rol r: Global.AN.obtenerTodosLosRoles()) {
			System.out.println(r);
			
//			for(Usuario u: r.getUsuarios()) {
//				System.out.println(u);
//			}
		}
	}

}
