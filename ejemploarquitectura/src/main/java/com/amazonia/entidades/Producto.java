package com.amazonia.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {
	// Variables de instancia
	private Long id;
	private String nombre;
	private LocalDate fechaCaducidad;
	private BigDecimal precio;

	// Source/Generate Constructor using Fields...	
	public Producto(Long id, String nombre, LocalDate fechaCaducidad, BigDecimal precio) {
		setId(id);
		setNombre(nombre);
		setFechaCaducidad(fechaCaducidad);
		setPrecio(precio);
	}
	
	// Source/Generate Setters y Getters...
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		if(id != null && id <= 0) {
			throw new RuntimeException("No se admiten ids inferiores a 1");
		}
		
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre == null || nombre.trim().length() == 0) {
			throw new RuntimeException("No se admiten nombres vacíos o nulos");
		}
		
		this.nombre = nombre;
	}
	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		if(fechaCaducidad != null && fechaCaducidad.isBefore(LocalDate.now())) {
			throw new RuntimeException("La fecha de caducidad no puede ser anterior a la actual");
		}
		
		this.fechaCaducidad = fechaCaducidad;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		if(precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("El precio es obligatorio y no puede ser menor que 0");
		}
		
		this.precio = precio;
	}
	
	// Source/Generate toString()...
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaCaducidad=" + fechaCaducidad + ", precio=" + precio
				+ "]";
	}
}
