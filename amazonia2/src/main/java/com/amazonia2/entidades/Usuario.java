package com.amazonia2.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {
	private Long id;
	private String nombre;
	private String email;
	private String password;
	
	private Rol rol;
}
