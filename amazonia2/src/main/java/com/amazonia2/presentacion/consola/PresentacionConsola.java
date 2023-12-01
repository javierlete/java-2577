package com.amazonia2.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.globales.Global;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PresentacionConsola {

	public static void main(String[] args) {
		for(Producto p: Global.UN.listadoProductos()) {
			System.out.println(p);
		}
		
		for(Rol r: Global.AN.obtenerTodosLosRoles()) {
			System.out.println(r);
			
//			for(Usuario u: r.getUsuarios()) {
//				System.out.println(u);
//			}
		}
		
		Producto producto = Producto.builder().codigoBarras("1234567890").nombre("aa").precio(new BigDecimal("10")).unidades(5).fechaCaducidad(LocalDate.now().plusYears(1)).build();
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<Producto>> errores = validator.validate(producto);
		
		for(ConstraintViolation<Producto> error: errores) {
			System.out.println("El campo " + error.getPropertyPath() + " " + error.getMessage());
		}
		
		if(errores.size() == 0) {
			System.out.println("El producto es válido");
			System.out.println(producto);
		}
	}

}
