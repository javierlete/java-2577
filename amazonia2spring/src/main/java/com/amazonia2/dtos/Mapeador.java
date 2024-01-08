package com.amazonia2.dtos;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Factura;
import com.amazonia2.entidades.Usuario;

public interface Mapeador {
	Factura carrito2Factura(Carrito carrito);
	Usuario cliente2Usuario(Cliente cliente);
	CarritoDTO carrito2CarritoDTO(Carrito carrito);
	Carrito carritoDTO2Carrito(CarritoDTO carritoDTO);
}
