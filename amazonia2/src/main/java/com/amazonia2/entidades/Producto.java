package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
	private Long id;
	private String codigoBarras;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;
	private Integer unidades;
}
