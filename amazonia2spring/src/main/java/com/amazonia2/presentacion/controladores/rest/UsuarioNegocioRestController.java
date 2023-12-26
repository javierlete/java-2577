package com.amazonia2.presentacion.controladores.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.UsuarioNegocio;

@RestController
@RequestMapping("/api/v2/negocio/usuario")
public class UsuarioNegocioRestController {
	@Autowired
	private Carrito carrito;

	@Autowired
	private UsuarioNegocio negocio;

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
	public Carrito agregarProductoACarrito(@PathVariable Long id) {
		negocio.agregarProductoACarrito(id, carrito);
		
		return sesionACarrito();
	}

	// Operación sin estado
	// Tú me das estado inicial
	// Yo te devuelvo estado final
	// Más limpia
	// Más pesada
	@PutMapping("/carrito/{id}/agregar")
	public Carrito agregarProductoACarrito(@PathVariable Long id, Carrito carrito) {
		return negocio.agregarProductoACarrito(id, carrito);
	}

	@GetMapping("/carrito")
	public Object obtenerCarrito() {
		return sesionACarrito();
	}

	private Carrito sesionACarrito() {
		System.out.println("CARRITO: " + carrito);
		Map<Long, Producto> mapa = new HashMap<>();

		for(Producto p: carrito.getProductos()) {
			mapa.put(p.getId(), p);
		}
		
		System.out.println(mapa);

		Carrito carritoADevolver = Carrito.builder().id(carrito.getId()).usuario(carrito.getUsuario()).productos(mapa).build();
		
		System.out.println("CARRITO A DEVOLVER: " + carritoADevolver);
		
		return carritoADevolver;
	}
}
