package com.amazonia2.logicanegocio;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Factura;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

public interface UsuarioNegocio {
	Iterable<Producto> listadoProductos();

	Producto detalleProducto(Long id);
	
	Usuario loguear(String email, String password);
	
	long cuantosUsuariosHay();
	
	long cuantosProductosHay();
	
	Carrito agregarProductoACarrito(Long id, Carrito carrito);
	Carrito agregarProductoACarrito(Producto producto, Carrito carrito);

	Carrito quitarProductoDeCarrito(Long id, Carrito carrito);
	Carrito quitarProductoDeCarrito(Producto producto, Carrito carrito);

	Carrito quitarUnidadDeProductoDeCarrito(Long id, Carrito carrito);

	Carrito agregarUnidadDeProductoDeCarrito(Long id, Carrito carrito);
	
	String nuevoNumeroFactura(String anno);

	Factura crearFacturaProForma(String name, Carrito carrito);
	
	Factura facturar(Factura factura);

	Usuario registrarUsuario(Usuario usuario);
}
