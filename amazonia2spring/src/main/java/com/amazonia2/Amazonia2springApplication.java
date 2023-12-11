package com.amazonia2;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.entidades.Producto;
import com.amazonia2.logicanegocio.AdminNegocio;

@SpringBootApplication
public class Amazonia2springApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Amazonia2springApplication.class, args);
	}

	@Autowired
	private DaoProducto dao;
	
	@Autowired
	private AdminNegocio negocio;
	
	@Override
	public void run(String... args) throws Exception {
		Producto producto = Producto.builder().nombre("Prueba").codigoBarras("1234567890128").precio(new BigDecimal("1234.12")).unidades(1).build();
		
//		dao.insertar(producto);
//		negocio.insertarProducto(producto);
		
		producto.setNombre("Modificado");
		
//		dao.modificar(producto);
		negocio.modificarProducto(producto);
		
		
//		for(Producto p: dao.obtenerTodos()) {
		for(Producto p: negocio.listadoProductos()) {
			System.out.println(p);
		}

//		dao.borrar(4L);
		negocio.borrarProducto(4L);
		
		for(Producto p: dao.obtenerCaducados()) {
			System.out.println(p);
		}

		for(Producto p: dao.obtenerCaducados(LocalDate.of(2000, 1, 2))) {
			System.out.println(p);
		}

		for(Producto p: dao.obtenerPorNombreParcial("odifi")) {
			System.out.println(p);
		}
		
//		System.out.println(dao.obtenerPorId(3L));
		System.out.println(negocio.detalleProducto(3L));
	}

}
