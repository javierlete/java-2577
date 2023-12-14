package com.amazonia2.presentacion.controladores.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.AdminNegocio;
import com.amazonia2.logicanegocio.ClaveDuplicadaException;

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
	public String post(Model modelo, @Valid Producto producto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("alerta", "Revisa los errores en el formulario");
			modelo.addAttribute("nivel", "danger");
			
			return "admin/detalle";
		}

		try {
			if (producto.getId() == null) {
				negocio.insertarProducto(producto);
			} else {
				negocio.modificarProducto(producto);
			}
		} catch (ClaveDuplicadaException e) {
			if (e.getCampo() != null) {
				modelo.addAttribute("alerta", "Revisa los errores en el formulario");
				modelo.addAttribute("nivel", "danger");
				
				bindingResult.addError(new FieldError(e.getObjeto(), e.getCampo(), e.getMessage()));
			} else {
				modelo.addAttribute("alerta", e.getMessage());
				modelo.addAttribute("nivel", "danger");
			}
			return "admin/detalle";
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
