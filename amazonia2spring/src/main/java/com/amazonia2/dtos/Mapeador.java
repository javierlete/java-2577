package com.amazonia2.dtos;

import java.util.Set;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Pedido;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Usuario;

public interface Mapeador {
	Usuario cliente2Usuario(Cliente cliente);
	CarritoDTO carrito2CarritoDTO(Carrito carrito);
	Carrito carritoDTO2Carrito(CarritoDTO carritoDTO);
	Pedido producto2Pedido(Producto producto);
	Set<Pedido> productos2Pedidos(Set<Producto> productos);
}
