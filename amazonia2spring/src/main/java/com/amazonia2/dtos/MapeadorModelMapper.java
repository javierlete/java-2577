package com.amazonia2.dtos;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Pedido;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

@Component
public class MapeadorModelMapper implements Mapeador {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public Set<Pedido> productos2Pedidos(Set<Producto> productos) {
		Set<Pedido> pedidos = new HashSet<>();
		Pedido pedido;
		
		for(Producto producto: productos) {
			pedido = producto2Pedido(producto);
			pedido.setId(null);
			pedidos.add(pedido);
		}
		
		return pedidos;
	}
	
	@Override
	public Pedido producto2Pedido(Producto producto) {
		return mapper.map(producto, Pedido.class);
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
