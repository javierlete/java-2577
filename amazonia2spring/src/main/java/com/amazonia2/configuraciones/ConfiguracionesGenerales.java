package com.amazonia2.configuraciones;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.amazonia2.entidades.Carrito;

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
	
	// TODO: Crear un interface con todas las operaciones de mapeado en el que se utilice este mapper u otro
	@Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
