package com.amazonia2.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.amazonia2.entidades.Carrito;

@ControllerAdvice
@Configuration
public class ConfiguracionesGenerales {
	@ModelAttribute
	private void carrito(Model modelo) {
		modelo.addAttribute(carrito);
	}
	
	@Autowired
	private Carrito carrito;
//	@Bean
//    LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        Locale locale = new Locale("es", "ES");
//        sessionLocaleResolver.setDefaultLocale(locale);
//        return sessionLocaleResolver;
//    }
}
