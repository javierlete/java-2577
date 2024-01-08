package com.amazonia2.configuraciones;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.amazonia2.entidades.Carrito;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;

@Log
@ControllerAdvice
@Configuration
public class ConfiguracionesGenerales {

	private Carrito carrito;

	public ConfiguracionesGenerales(Carrito carrito) {
		this.carrito = carrito;
	}

	@ModelAttribute
	private void carrito(Model modelo) {
		modelo.addAttribute(carrito);
	}

	@ExceptionHandler(Exception.class)
	public String handleApplicationException(Model modelo, Exception exception, HttpServletRequest request) {
		log.severe("EXCEPTION");

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status != null) {
			log.severe("Status: " + status.toString());
		}

		modelo.addAttribute("status", status);

		exception.printStackTrace();

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		exception.printStackTrace(pw);

		modelo.addAttribute("exception", exception);
		modelo.addAttribute("stack", sw.toString());

		return "_error";
	}
}
