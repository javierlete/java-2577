package com.amazonia2.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.logicanegocio.UsuarioNegocio;

@Controller
public class ComprasController {
	
	@Autowired
	private Carrito carrito;
	
	@Autowired
	private UsuarioNegocio negocio;
	
	@GetMapping("/carrito")
	public String procesarCarrito(Long idProducto, Model modelo) {
		if(idProducto != null) {
			negocio.agregarProductoACarrito(idProducto, carrito);
		}
		
		return "carrito";
	}
}
