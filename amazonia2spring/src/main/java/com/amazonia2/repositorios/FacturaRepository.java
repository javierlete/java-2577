package com.amazonia2.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.amazonia2.entidades.Factura;

@RepositoryRestResource(collectionResourceRel = "facturas", path = "facturas")
public interface FacturaRepository extends CrudRepository<Factura, Long>, PagingAndSortingRepository<Factura, Long> {
	@Query("select max(f.numero) from Factura f where f.numero LIKE :anno + '%'")
	String ultimoNumeroFactura(String anno);
}
