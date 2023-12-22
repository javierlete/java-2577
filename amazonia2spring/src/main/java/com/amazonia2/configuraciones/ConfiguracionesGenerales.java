package com.amazonia2.configuraciones;

import org.springframework.context.annotation.Bean;
//import java.util.Locale;
//
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.amazonia2.entidades.Carrito;

@Configuration
public class ConfiguracionesGenerales {
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	Carrito carrito() {
		return new Carrito();
	}
	
//	@Bean
//    LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        Locale locale = new Locale("es", "ES");
//        sessionLocaleResolver.setDefaultLocale(locale);
//        return sessionLocaleResolver;
//    }
}
