package com.amazonia2.entidades;

import com.amazonia2.bibliotecas.validaciones.DniValido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Cliente extends Usuario {
	@NotNull
	@NotBlank
	@DniValido
	private String dni;
}
