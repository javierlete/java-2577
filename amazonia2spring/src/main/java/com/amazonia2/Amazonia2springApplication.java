package com.amazonia2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amazonia2.entidades.Producto;
import com.amazonia2.repositorios.ProductoRepository;

@SpringBootApplication
public class Amazonia2springApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Amazonia2springApplication.class, args);
	}

//	@Autowired
//	private DaoProducto dao;
//	
//	@Autowired
//	private AdminNegocio negocio;
//	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProductoRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		Iterable<Producto> productos = repo.findAll();
		
		for(Producto p: productos) {
			System.out.println(p);
		}
		
		System.out.println("Más caro");
		
		System.out.println(repo.findFirstByOrderByPrecioDesc());
		System.out.println(repo.obtenerProductoMasCaro());
		
		String pass = passwordEncoder.encode("contra");
		
		System.out.println(pass);
		
		System.out.println(passwordEncoder.matches("contra", pass));
		
//		Producto producto = Producto.builder().nombre("Prueba").codigoBarras("1234567890128").precio(new BigDecimal("1234.12")).unidades(1).build();
//		
////		producto = dao.insertar(producto);
//		producto = negocio.insertarProducto(producto);
//		
//		producto.setNombre("Modificado");
//		
////		dao.modificar(producto);
//		negocio.modificarProducto(producto);
//		
////		for(Producto p: dao.obtenerTodos()) {
//		for(Producto p: negocio.listadoProductos()) {
//			System.out.println(p);
//		}
//
////		System.out.println(dao.obtenerPorId(3L));
//		System.out.println(negocio.detalleProducto(producto.getId()));
//
////		dao.borrar(4L);
//		negocio.borrarProducto(producto.getId());
//		
//		for(Producto p: dao.obtenerCaducados()) {
//			System.out.println(p);
//		}
//
//		for(Producto p: dao.obtenerCaducados(LocalDate.of(2000, 1, 2))) {
//			System.out.println(p);
//		}
//
//		for(Producto p: dao.obtenerPorNombreParcial("odifi")) {
//			System.out.println(p);
//		}
//		
	}

}
