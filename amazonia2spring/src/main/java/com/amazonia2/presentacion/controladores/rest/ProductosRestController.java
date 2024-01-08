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
import com.amazonia2.logicanegocio.UsuarioNegocio;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosRestController {
	
	private UsuarioNegocio negocioUsuario;
	private AdminNegocio negocioAdmin;
	
	public ProductosRestController(AdminNegocio negocioAdmin, UsuarioNegocio negocioUsuario) {
		this.negocioAdmin = negocioAdmin;
		this.negocioUsuario = negocioUsuario;
	}
	
	@GetMapping
	public Iterable<Producto> getTodos() {
		return negocioUsuario.listadoProductos();
	}
	
	@GetMapping("/{id}")
	public Producto getId(@PathVariable Long id) {
		return negocioUsuario.detalleProducto(id);
	}
	
	@PostMapping
	public Producto post(@RequestBody Producto producto) {
		return negocioAdmin.insertarProducto(producto);
	}
	
	@PutMapping("/{id}")
	public Producto put(@PathVariable Long id, @RequestBody Producto producto) {
		return negocioAdmin.modificarProducto(producto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		negocioAdmin.borrarProducto(id);
	}
	
	@GetMapping("/buscar/cuantos")
	public long buscarCuantos() {
		return negocioUsuario.cuantosProductosHay();
	}
	
}
