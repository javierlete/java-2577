package com.amazonia2.bibliotecas.validaciones;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidoValidator implements ConstraintValidator<DniValido, String> {

	private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
	private static final String REGEXP = "^[XYZ\\d]\\d{7}[" + LETRAS + "]$";

	@Override
	public boolean isValid(String dni, ConstraintValidatorContext context) {
		if (dni == null || dni.length() == 0) {
			return true;
		}

		if (!dni.matches(REGEXP)) {
			return false;
		}

		char letra = dni.charAt(8);
		char digitoExtranjero;
		String sNumeroComun = dni.substring(1, 8);

		switch (dni.charAt(0)) {
		case 'X':
			digitoExtranjero = '0';
			break;
		case 'Y':
			digitoExtranjero = '1';
			break;
		case 'Z':
			digitoExtranjero = '2';
			break;
		default:
			digitoExtranjero = dni.charAt(0);
		}

		// String sNumero = new
		// StringBuffer().append(digitoExtranjero).append(sNumeroComun).toString();
		String sNumero = String.valueOf(digitoExtranjero) + sNumeroComun;

		int numero = Integer.parseInt(sNumero); // sNumero.replace('X', '0').replace('Y', '1').replace('Z', '2'));
		int posicion = numero % 23;

		return letra == LETRAS.charAt(posicion);
	}

}
