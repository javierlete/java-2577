package com.amazonia2.entidades;

import com.amazonia2.bibliotecas.validaciones.DniValido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@Entity
public class Cliente extends Usuario {
	@NotNull
	@NotBlank
	@DniValido(groups = RegistroGrupoValidacion.class)
	@Column(columnDefinition = "CHAR(9)")
	private String dni;
}
