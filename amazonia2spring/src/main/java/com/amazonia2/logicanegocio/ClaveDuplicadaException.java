package com.amazonia2.logicanegocio;

import org.springframework.dao.DuplicateKeyException;

import lombok.Getter;

public class ClaveDuplicadaException extends LogicaNegocioException {
	@Getter
	private final String objeto;
	@Getter
	private final String campo;
	
	public ClaveDuplicadaException(String mensaje, String objeto, String campo, DuplicateKeyException e) {
		super(mensaje, e);
		
		this.objeto = objeto;
		this.campo = campo;
	}

	private static final long serialVersionUID = -8771998068136867877L;
}
