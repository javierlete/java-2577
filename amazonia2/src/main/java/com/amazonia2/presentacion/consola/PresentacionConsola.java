package com.amazonia2.presentacion.consola;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.globales.Global;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PresentacionConsola {

	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = validatorFactory.getValidator();

	public static void main(String[] args) {
		for (Producto p : Global.UN.listadoProductos()) {
			System.out.println(p);
		}

		for (Rol r : Global.AN.obtenerTodosLosRoles()) {
			System.out.println(r);

//			for(Usuario u: r.getUsuarios()) {
//				System.out.println(u);
//			}
		}

		Producto producto = Producto.builder().codigoBarras("1234567890128").nombre("aa").precio(new BigDecimal("10"))
				.unidades(5).fechaCaducidad(LocalDate.now().plusYears(1)).build();

		validar(producto);

		Global.AN.insertarProducto(producto);

		Rol rol = Rol.builder().nombre("USER").build();
		Cliente cliente = Cliente.builder().password("asdf").dni("12345678Z").email("a@b").nombre("asdf").rol(rol)
				.build();

		if (validar(rol) && validar(cliente)) {
			Global.FABRICA.obtenerDaoRol().insertar(rol);
			Global.FABRICA.obtenerDaoUsuario().insertar(cliente);
		}
	}

	private static boolean validar(Object objeto) {
		System.out.println(objeto.getClass().getSimpleName());
		
		Set<ConstraintViolation<Object>> errores = validator.validate(objeto);

		for (ConstraintViolation<Object> error : errores) {
			System.out.println("\tEl campo " + error.getPropertyPath() + " " + error.getMessage());
		}

		if (errores.size() == 0) {
			System.out.println("El objeto es válido");
			System.out.println(objeto);

			return true;
		} else {
			return false;
		}
	}

}
