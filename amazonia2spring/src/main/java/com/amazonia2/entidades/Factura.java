package com.amazonia2.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Entity
public class Factura extends Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// YYYYNNNN
	@Pattern(regexp = "^\\d{8}$")
	@Column(columnDefinition = "CHAR(8)")
	private String numero;
	
	@FutureOrPresent
	private LocalDate fecha;
	
	public Cliente getCliente() {
		return (Cliente) getUsuario();
	}
	
	public void setCliente(Cliente cliente) {
		setUsuario(cliente);
	}
}
