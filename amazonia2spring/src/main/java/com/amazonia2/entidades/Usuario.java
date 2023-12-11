package com.amazonia2.entidades;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Usuario {
	private Long id;

	@NotNull
	@Size(min = 2, max = 20)
	private String nombre;

	@NotNull
	@NotBlank
	@Email
	@Size(max = 50)
	private String email;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String password;

	@NotNull
	private Rol rol;
}
