package com.amazonia2.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Factura;
import com.amazonia2.entidades.Usuario;

@Component
public class MapeadorModelMapper implements Mapeador {

	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public Factura carrito2Factura(Carrito carrito) {
		return mapper.map(carrito, Factura.class);
	}

	@Override
	public Usuario cliente2Usuario(Cliente cliente) {
		Usuario usuario = new Usuario();
		mapper.map(cliente, usuario);
		return usuario;
	}

	@Override
	public CarritoDTO carrito2CarritoDTO(Carrito carrito) {
		return mapper.map(carrito, CarritoDTO.class);
	}

	@Override
	public Carrito carritoDTO2Carrito(CarritoDTO carritoDTO) {
		Carrito carritoLocal = mapper.map(carritoDTO, Carrito.class);
		
		carritoDTO.getProductos().stream().forEach(carritoLocal::addProducto);
		
		return carritoLocal;
	}

}
