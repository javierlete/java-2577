package com.amazonia2.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.amazonia2.entidades.Pedido;

@RepositoryRestResource(collectionResourceRel = "pedidos", path = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long>, PagingAndSortingRepository<Pedido, Long> {
}
