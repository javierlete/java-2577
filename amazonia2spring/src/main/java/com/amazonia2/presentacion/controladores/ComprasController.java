package com.amazonia2.presentacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.logicanegocio.UsuarioNegocio;

@Controller
public class ComprasController {
	
	private Carrito carrito;
	private UsuarioNegocio negocio;
	
	public ComprasController(Carrito carrito, UsuarioNegocio negocio) {
		this.carrito = carrito;
		this.negocio = negocio;
	}
	
	@GetMapping("/carrito")
	public String procesarCarrito(Long idProducto) {
		if(idProducto != null) {
			negocio.agregarProductoACarrito(idProducto, carrito);
		}
		
		return "carrito";
	}
}
