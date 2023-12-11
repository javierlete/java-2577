package com.amazonia2.entidades;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Rol {
	private Long id;

	@NotNull
	@Size(min = 2, max = 20)
	private String nombre;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Usuario> usuarios;
}
