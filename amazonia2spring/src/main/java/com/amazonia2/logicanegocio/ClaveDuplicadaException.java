package com.amazonia2.logicanegocio;

import org.springframework.dao.DuplicateKeyException;

import lombok.Getter;
import lombok.experimental.StandardException;

@StandardException
public class ClaveDuplicadaException extends LogicaNegocioException {
	@Getter
	private String objeto, campo;
	
	public ClaveDuplicadaException(String mensaje, String objeto, String campo, DuplicateKeyException e) {
		this(mensaje, e);
		
		this.objeto = objeto;
		this.campo = campo;
	}

	private static final long serialVersionUID = -8771998068136867877L;
}
