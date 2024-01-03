package com.amazonia2.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(groups = RegistroGrupoValidacion.class)
	@Size(min = 2, max = 20, groups = RegistroGrupoValidacion.class)
	private String nombre;

	@NotNull(groups = RegistroGrupoValidacion.class)
	@NotBlank(groups = RegistroGrupoValidacion.class)
	@Email(groups = RegistroGrupoValidacion.class)
	@Size(max = 50, groups = RegistroGrupoValidacion.class)
	private String email;

	@NotNull(groups = RegistroGrupoValidacion.class)
	@NotBlank(groups = RegistroGrupoValidacion.class)
	@Size(max = 20, groups = RegistroGrupoValidacion.class)
	private String password;

	@NotNull
	@ManyToOne
	@Builder.Default
	private Rol rol = Rol.USUARIO;
}
