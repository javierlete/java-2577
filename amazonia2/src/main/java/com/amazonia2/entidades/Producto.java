package com.amazonia2.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Producto {
	private static final Integer UNIDADES_POR_DEFECTO = null;
	
	private Long id;
	private String codigoBarras;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;
	private Integer unidades;
	
	public Producto(Long id, String codigoBarras, String nombre, BigDecimal precio, LocalDate fechaCaducidad,
			Integer unidades) {
		setId(id);
		setCodigoBarras(codigoBarras);
		setNombre(nombre);
		setPrecio(precio);
		setFechaCaducidad(fechaCaducidad);
		setUnidades(unidades);
	}

	public Producto(String codigoBarras, String nombre, BigDecimal precio, LocalDate fechaCaducidad, Integer unidades) {
		this(null, codigoBarras, nombre, precio, fechaCaducidad, unidades);
	}
	
	public Producto(String nombre, BigDecimal precio, LocalDate fechaCaducidad, Integer unidades) {
		this(null, null, nombre, precio, fechaCaducidad, unidades);
	}

	public Producto(String nombre, BigDecimal precio, LocalDate fechaCaducidad) {
		this(null, null, nombre, precio, fechaCaducidad, UNIDADES_POR_DEFECTO);
	}

	public Producto(String nombre, BigDecimal precio, Integer unidades) {
		this(null, null, nombre, precio, null, unidades);
	}

	public Producto(String nombre, BigDecimal precio) {
		this(null, null, nombre, precio, null, UNIDADES_POR_DEFECTO);
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarras, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(codigoBarras, other.codigoBarras) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", precio=" + precio
				+ ", fechaCaducidad=" + fechaCaducidad + ", unidades=" + unidades + "]";
	}
}
