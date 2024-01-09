package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Entity
@Table(name = "facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// YYYYNNNN
	@NotNull
	@Pattern(regexp = "^\\d{8}$")
	@Column(columnDefinition = "CHAR(8)", unique = true)
	private String numero;
	
	@NotNull
	@FutureOrPresent
	private LocalDate fecha;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "factura")
	@Builder.Default
	private Set<Pedido> pedidos = new HashSet<>();
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for(Pedido p: pedidos) {
			total = total.add(p.getTotal());
		}
		
		return total;
	}
}
