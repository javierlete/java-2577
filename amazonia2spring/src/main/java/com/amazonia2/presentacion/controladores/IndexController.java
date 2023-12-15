package com.amazonia2.presentacion.controladores;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia2.logicanegocio.UsuarioNegocio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping
public class IndexController implements ErrorController {
	@Autowired
	private UsuarioNegocio negocio;
	
//	@ResponseBody
	@GetMapping("/")
	public String index(Model modelo) {
//		return negocio.listadoProductos().toString();
		modelo.addAttribute("productos", negocio.listadoProductos());
		return "index";
	}
	
//	@ResponseBody
	@GetMapping("/detalle")
	public String detalle(Model modelo, Long id) {
		modelo.addAttribute("producto", negocio.detalleProducto(id));
		return "detalle";
	}
	
	@GetMapping("/error")
	public String error(Model modelo, HttpServletRequest request, Exception exception) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		log.severe("Status: " + status.toString());
		log.severe("Mensaje: " + exception.getMessage());
		log.log(Level.SEVERE, "Detalles de la excepción", exception);
		
		modelo.addAttribute("status", status);
		
		return "_error";
	}
}
