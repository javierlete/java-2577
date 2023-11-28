package com.amazonia2.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
	private Long id;
	private String nombre;
	private String email;
	private String password;
	
	private Rol rol;
}
