package com.amazonia2.presentacion.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Factura;
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
	
	@GetMapping("/carrito/borrar")
	public String borrarCarrito(Long id) {
		negocio.quitarProductoDeCarrito(id, carrito);
		return "redirect:/carrito";
	}
	
	@GetMapping("/pagar")
	public String pagar(Model modelo, Authentication auth) {
		Factura factura = negocio.crearFacturaProForma(auth.getName(), carrito);		
		
		modelo.addAttribute("factura", factura);
		return "pagar";
	}
	
	@GetMapping("/confirmar")
	public String confirmar(Model modelo, Authentication auth) {
		pagar(modelo, auth);
		
		negocio.facturar((Factura)modelo.getAttribute("factura"));
		
		carrito.vaciarCarrito();
		
		modelo.addAttribute("alerta", "La factura se ha registrado correctamente");
		modelo.addAttribute("nivel", "success");
		
		return "pagar";
	}
}
