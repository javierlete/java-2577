package com.amazonia2.presentacion.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.amazonia2.bibliotecas.Alerta;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.RegistroGrupoValidacion;
import com.amazonia2.logicanegocio.ClaveDuplicadaException;
import com.amazonia2.logicanegocio.UsuarioNegocio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

@Log
@Controller
public class IndexController implements ErrorController {

	private static final String LOGIN = "login";
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
	public String login(Cliente cliente, String error, String logout, String noautorizado, String interactivo,
			Model modelo) {
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

		return LOGIN;
	}

	@PostMapping("/registro")
	public String registro(@Validated(RegistroGrupoValidacion.class) Cliente cliente, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			try {
				negocio.registrarUsuario(cliente);
			} catch (ClaveDuplicadaException e) {
				if (e.getCampo() != null) {
					bindingResult.addError(new FieldError(e.getObjeto(), e.getCampo(), e.getMessage()));
				} else {
					throw e;
				}
			}
		}
		return LOGIN;
	}

	@GetMapping("/error")
	public String error(Model modelo, HttpServletRequest request) {
		log.severe("ERROR");

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		log.severe("Status: " + status.toString());

		modelo.addAttribute("status", status);

		return "_error";
	}
}
