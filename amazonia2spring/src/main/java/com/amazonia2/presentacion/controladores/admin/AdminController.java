package com.amazonia2.presentacion.controladores.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.AdminNegocio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminNegocio negocio;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("productos", negocio.listadoProductos());
		return "admin/index";
	}

	@PostMapping
	public String post(@Valid Producto producto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "admin/detalle";
		}
		
		if(producto.getId() == null) {
			negocio.insertarProducto(producto);
		} else {
			negocio.modificarProducto(producto);
		}
		
		return "redirect:/admin";
	}

	@GetMapping("/borrar")
	public String borrar(Long id) {
		negocio.borrarProducto(id);
		
		return "redirect:/admin";
	}
	
	
	@GetMapping("/detalle")
	public String detalle(Model modelo, Long id, Producto producto) {
		if (id != null) {
			modelo.addAttribute("producto", negocio.detalleProducto(id));
		}
		
		return "admin/detalle";
	}
	
}
