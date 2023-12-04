package com.amazonia2.entidades;

import com.amazonia2.bibliotecas.validaciones.DniValido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {
	@NotNull
	@NotBlank
	@DniValido
	@Column(unique = true, columnDefinition = "CHAR(9)")
	private String dni;
}
