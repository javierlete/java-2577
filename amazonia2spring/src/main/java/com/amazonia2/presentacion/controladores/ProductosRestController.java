package com.amazonia2.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/productos")
public class ProductosRestController {
	@Autowired
	private AdminNegocio negocio;
	
	@GetMapping
	public Iterable<Producto> getTodos() {
		return negocio.listadoProductos();
	}
	
	@GetMapping("/{id}")
	public Producto getId(@PathVariable Long id) {
		return negocio.detalleProducto(id);
	}
	
	@PostMapping
	public Producto post(@RequestBody Producto producto) {
		return negocio.insertarProducto(producto);
	}
	
	@PutMapping("/{id}")
	public Producto put(@PathVariable Long id, @RequestBody Producto producto) {
		return negocio.modificarProducto(producto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		negocio.borrarProducto(id);
	}
	
	@GetMapping("/buscar/cuantos")
	public long buscarCuantos() {
		return negocio.cuantosProductosHay();
	}
	
}
