package com.amazonia2.presentacion.controladores.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.AdminNegocio;
import com.amazonia2.logicanegocio.ClaveDuplicadaException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final String NIVEL = "nivel";
	private static final String DANGER = "danger";
	private static final String ALERTA = "alerta";
	private static final String ADMIN_DETALLE = "admin/detalle";
	
	private AdminNegocio negocio;
	
	public AdminController(AdminNegocio negocio) {
		this.negocio = negocio;
	}

	@ModelAttribute("miLocale")
	public String requestURI(final HttpServletRequest request) {
	   return request.getLocale().toString();
	}
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("productos", negocio.listadoProductos());
		return "admin/index";
	}

	@PostMapping
	public String post(Model modelo, @Valid Producto producto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute(ALERTA, "Revisa los errores en el formulario");
			modelo.addAttribute(NIVEL, DANGER);
			
			return ADMIN_DETALLE;
		}

		try {
			if (producto.getId() == null) {
				negocio.insertarProducto(producto);
			} else {
				negocio.modificarProducto(producto);
			}
		} catch (ClaveDuplicadaException e) {
			if (e.getCampo() != null) {
				modelo.addAttribute(ALERTA, "Revisa los errores en el formulario");
				modelo.addAttribute(NIVEL, DANGER);
				
				bindingResult.addError(new FieldError(e.getObjeto(), e.getCampo(), e.getMessage()));
			} else {
				modelo.addAttribute(ALERTA, e.getMessage());
				modelo.addAttribute(NIVEL, DANGER);
			}
			return ADMIN_DETALLE;
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

		return ADMIN_DETALLE;
	}

}
