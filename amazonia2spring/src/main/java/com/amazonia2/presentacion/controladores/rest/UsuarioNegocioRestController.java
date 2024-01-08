package com.amazonia2.presentacion.controladores.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonia2.dtos.CarritoDTO;
import com.amazonia2.dtos.Mapeador;
import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.UsuarioNegocio;

@RestController
@RequestMapping("/api/v2/negocio/usuario")
public class UsuarioNegocioRestController {
	private Carrito carrito;

	private UsuarioNegocio negocio;

	private Mapeador mapeador;
	
	public UsuarioNegocioRestController(Carrito carrito, UsuarioNegocio negocio, Mapeador mapeador) {
		this.carrito = carrito;
		this.negocio = negocio;
		this.mapeador= mapeador;
	}

	@GetMapping("/productos")
	public Iterable<Producto> listadoProductos() {
		return negocio.listadoProductos();
	}

	@GetMapping("/productos/{id}")
	public Producto detalleProducto(@PathVariable Long id) {
		return negocio.detalleProducto(id);
	}

	// Rendimiento
	// Más ligero
	@GetMapping("/carrito/{id}/agregar")
	public CarritoDTO agregarProductoACarrito(@PathVariable Long id) {
		negocio.agregarProductoACarrito(id, carrito);

		return sesionACarritoDTO();
	}

	// Operación sin estado
	// Tú me das estado inicial
	// Yo te devuelvo estado final
	// Más limpia
	// Más pesada
	@PutMapping("/carrito/{id}/agregar")
	public CarritoDTO agregarProductoACarrito(@PathVariable Long id, @RequestBody CarritoDTO carritoDTO) {
		return mapeador.carrito2CarritoDTO(negocio.agregarProductoACarrito(id, mapeador.carritoDTO2Carrito(carritoDTO)));
	}

	@GetMapping("/carrito")
	public Object obtenerCarrito() {
		return sesionACarritoDTO();
	}
	
	@GetMapping("/carrito/{id}/quitar-unidad")
	public CarritoDTO quitarUnidad(@PathVariable Long id) {
		return mapeador.carrito2CarritoDTO(negocio.quitarUnidadDeProductoDeCarrito(id, carrito));
	}

	@GetMapping("/carrito/{id}/agregar-unidad")
	public CarritoDTO agregarUnidad(@PathVariable Long id) {
		return mapeador.carrito2CarritoDTO(negocio.agregarUnidadDeProductoDeCarrito(id, carrito));
	}

	private CarritoDTO sesionACarritoDTO() {
		return mapeador.carrito2CarritoDTO(carrito);
	}
}
