package com.amazonia2.bibliotecas;

import org.springframework.ui.Model;

public class Alerta {
	private Model modelo;
	
	public Alerta(Model modelo) {
		this.modelo = modelo;
	}
	
	public void alertar(String nivel, String mensaje) {
		modelo.addAttribute("alerta", mensaje);
		modelo.addAttribute("nivel", nivel);
	}
	
	public void success(String mensaje) {
		alertar("success", mensaje);
	}
	
	public void danger(String mensaje) {
		alertar("danger", mensaje);
	}

	public void warning(String mensaje) {
		alertar("warning", mensaje);
	}

	public void info(String mensaje) {
		alertar("info", mensaje);
	}
}
