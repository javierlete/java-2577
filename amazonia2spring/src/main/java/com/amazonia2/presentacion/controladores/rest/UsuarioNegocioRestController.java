package com.amazonia2.presentacion.controladores.rest;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonia2.dtos.CarritoDTO;
import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.UsuarioNegocio;

@RestController
@RequestMapping("/api/v2/negocio/usuario")
public class UsuarioNegocioRestController {
	private Carrito carrito;

	private UsuarioNegocio negocio;

	public UsuarioNegocioRestController(Carrito carrito, UsuarioNegocio negocio) {
		this.carrito = carrito;
		this.negocio = negocio;
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

		return sesionACarrito();
	}

	// Operación sin estado
	// Tú me das estado inicial
	// Yo te devuelvo estado final
	// Más limpia
	// Más pesada
	@PutMapping("/carrito/{id}/agregar")
	public CarritoDTO agregarProductoACarrito(@PathVariable Long id, Carrito carrito) {
		return carritoACarritoDTO(negocio.agregarProductoACarrito(id, carrito));
	}

	@GetMapping("/carrito")
	public Object obtenerCarrito() {
		return sesionACarrito();
	}

	private CarritoDTO sesionACarrito() {
		return carritoACarritoDTO(carrito);
	}

	// Mapeador de Carrito a CarritoDTO
	private ModelMapper mapper = new ModelMapper();

	private CarritoDTO carritoACarritoDTO(Carrito carrito) {
		return mapper.map(carrito, CarritoDTO.class);
	}
}
