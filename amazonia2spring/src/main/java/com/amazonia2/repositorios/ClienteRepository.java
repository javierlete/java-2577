package com.amazonia2.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.amazonia2.entidades.Cliente;

@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	Cliente findByEmail(String email);
}
