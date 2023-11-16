package com.amazonia.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Producto {
	// Variables de instancia
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;

	// Source/Generate Constructor using Fields...
	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad) {
		setId(id);
		setNombre(nombre);
		setFechaCaducidad(fechaCaducidad);
		setPrecio(precio);
	}
	
	public Producto(Producto producto) {
		this(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getFechaCaducidad());
	}
	
	public Producto(Long id, String nombre, LocalDate fechaCaducidad, BigDecimal precio) {
		this(id, nombre, precio, fechaCaducidad);
	}

	public Producto(String nombre, BigDecimal precio, LocalDate fechaCaducidad) {
		this(null, nombre, fechaCaducidad, precio);
	}

	public Producto(String nombre, LocalDate fechaCaducidad, BigDecimal precio) {
		this(null, nombre, fechaCaducidad, precio);
	}

	public Producto(String nombre, BigDecimal precio) {
		this(null, nombre, null, precio);
	}

	public Producto(String nombre) {
		this(null, nombre, null, BigDecimal.ZERO);
	}

	public Producto() {
		this(null, "ANÓNIMO", null, BigDecimal.ZERO);
	}

	// Source/Generate Setters y Getters...
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id <= 0) {
			throw new RuntimeException("No se admiten ids inferiores a 1");
		}

		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el valor del nombre del producto
	 * @param nombre debe estar rellenado
	 * @throws RuntimeException en el caso de que no esté rellenado
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().length() == 0) {
			throw new RuntimeException("No se admiten nombres vacíos o nulos");
		}

		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del producto
	 * @param precio debe ser rellenado y mayor o igual que 0
	 * @throws RuntimeException en el caso de que el precio no cumpla las condiciones requeridas
	 */
	public void setPrecio(BigDecimal precio) {
		if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("El precio es obligatorio y no puede ser menor que 0");
		}
	
		this.precio = precio;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		if (fechaCaducidad != null && fechaCaducidad.isBefore(LocalDate.now())) {
			throw new RuntimeException("La fecha de caducidad no puede ser anterior a la actual");
		}

		this.fechaCaducidad = fechaCaducidad;
	}

	// Source/Generate hashCode and equals...
	@Override
	public int hashCode() {
		return Objects.hash(fechaCaducidad, id, nombre, precio);
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
		return Objects.equals(fechaCaducidad, other.fechaCaducidad) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}

	// Source/Generate toString()...
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaCaducidad=" + fechaCaducidad + ", precio=" + precio
				+ "]";
	}
}