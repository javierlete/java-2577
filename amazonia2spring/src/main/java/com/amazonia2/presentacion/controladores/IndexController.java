package com.amazonia2.presentacion.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia2.bibliotecas.Alerta;
import com.amazonia2.logicanegocio.UsuarioNegocio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping
public class IndexController implements ErrorController {

	private UsuarioNegocio negocio;

	public IndexController(UsuarioNegocio negocio) {
		this.negocio = negocio;
	}

	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("productos", negocio.listadoProductos());
		return "index";
	}

	@GetMapping("/detalle/{id}")
	public String detalle(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("producto", negocio.detalleProducto(id));
		return "detalle";
	}

	@GetMapping("/login")
	public String login(String error, String logout, String noautorizado, String interactivo, Model modelo) {
		Alerta alerta = new Alerta(modelo);

		if (error != null) {
			alerta.danger("El usuario o la contraseña no son correctos");
		} else if (logout != null) {
			alerta.success("Se ha desconectado de la sesión correctamente");
		} else if (noautorizado != null) {
			alerta.danger("Tu nivel de acceso no es suficiente");
		} else if (interactivo != null) {
			// No hay que hacer nada en este caso
		} else {
			alerta.warning("Tienes que iniciar sesión");
		}

		return "login";
	}

	@GetMapping("/error")
	public String error(Model modelo, HttpServletRequest request, Exception exception) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		log.severe("Status: " + status.toString());

		log.severe("Tipo: " + exception.getClass().getSimpleName());
		log.severe("Mensaje: " + exception.getMessage());

		modelo.addAttribute("status", status);

		return "_error";
	}
}
