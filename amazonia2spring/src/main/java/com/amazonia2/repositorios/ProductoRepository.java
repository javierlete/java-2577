package com.amazonia2.repositorios;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.amazonia2.entidades.Producto;


public interface ProductoRepository extends CrudRepository<Producto, Long>, PagingAndSortingRepository<Producto, Long> {
	Iterable<Producto> findByNombreContainsIgnoreCase(String nombre);
	
	default Iterable<Producto> obtenerCaducados() {
		return findByFechaCaducidadBefore(LocalDate.now());
	}
	
	// Iterable<Producto> findByFechaCaducidadBefore();
	
	Iterable<Producto> findByFechaCaducidadBefore(LocalDate fecha);
	
	//@Query(nativeQuery = true, "SELECT MAX(precio) FROM productos")
	@Query("select p from Producto p where p.precio = (select max(p2.precio) from Producto p2)")
	Producto obtenerProductoMasCaro();
	
	Producto findFirstByOrderByPrecioDesc();
	
//	default Producto findTopOrderByPrecioDesc() {
//		return findTopOrderByPrecioDesc(1);
//	}
}
