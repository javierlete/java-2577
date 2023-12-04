package com.amazonia2.bibliotecas.validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class GestionErrores<T> {
	public Map<String, String> convertirAErrores(Set<ConstraintViolation<T>> validaciones) {
		var errores = new HashMap<String, String>();
		
		validaciones.stream().forEach(validacion -> errores.put(validacion.getPropertyPath().toString(), validacion.getMessage()));
		
		return errores;
	}
}
