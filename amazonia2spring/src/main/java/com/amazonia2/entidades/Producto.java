package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.EAN;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Producto {
	private Long id;
	
	@NotNull
	@Pattern(regexp = "^\\d{13}$", message = "debe tener 13 dígitos")
	@Size(min = 13, max = 13, message = "debe ser 13 caracteres exactos")
	@EAN
	private String codigoBarras;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;
	
	@NotNull
	@Min(0)
	private BigDecimal precio;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Future
	private LocalDate fechaCaducidad;
	
	@NotNull
	@Min(0)
	private Integer unidades;
}
