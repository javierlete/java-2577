package com.amazonia2.presentacion.controladores.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.AdminNegocio;

@RestController
@RequestMapping("/api/v2/negocio/admin")
public class AdminNegocioRestController {
	
	private AdminNegocio negocio;
	
	public AdminNegocioRestController(AdminNegocio negocio) {
		this.negocio = negocio;
	}
	
	@GetMapping("/productos")
	public Iterable<Producto> getTodos() {
		return negocio.listadoProductos();
	}
	
	@GetMapping("/productos/{id}")
	public Producto getId(@PathVariable Long id) {
		return negocio.detalleProducto(id);
	}
	
	@PostMapping("/productos")
	public Producto post(@RequestBody Producto producto) {
		return negocio.insertarProducto(producto);
	}
	
	@PutMapping("/productos/{id}")
	public Producto put(@PathVariable Long id, @RequestBody Producto producto) {
		return negocio.modificarProducto(producto);
	}

	@DeleteMapping("/productos/{id}")
	public void delete(@PathVariable Long id) {
		negocio.borrarProducto(id);
	}
	
	@GetMapping("/productos/buscar/cuantos")
	public long buscarCuantos() {
		return negocio.cuantosProductosHay();
	}
	
}
