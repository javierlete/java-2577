package com.amazonia2.entidades;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.EAN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Pattern(regexp = "^\\d{13}$", message = "debe tener 13 dígitos")
	@Size(min = 13, max = 13, message = "debe ser 13 caracteres exactos")
	@EAN
	@Column(columnDefinition = "CHAR(13)")
	private String codigoBarras;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String nombre;
	
	@NotNull
	@Min(0)
	private BigDecimal precio;
	
	@NotNull
	@Min(0)
	private Integer unidades;
	
	@NotNull
	@ManyToOne
	private Factura factura;
	
	public BigDecimal getTotal() {
		return precio.multiply(new BigDecimal(unidades));
	}
}
