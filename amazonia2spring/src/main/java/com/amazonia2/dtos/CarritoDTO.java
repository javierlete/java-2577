package com.amazonia2.dtos;

import java.util.Set;

import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

import lombok.Data;

@Data
public class CarritoDTO {
	private Long id;
	private Usuario usuario;
	private Set<Producto> productos;
}
