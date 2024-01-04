package com.amazonia2.logicanegocio;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.amazonia2.configuraciones.WebSecurityConfig;
import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Cliente;
import com.amazonia2.entidades.Factura;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.entidades.Usuario;
import com.amazonia2.repositorios.ClienteRepository;
import com.amazonia2.repositorios.FacturaRepository;
import com.amazonia2.repositorios.ProductoRepository;
import com.amazonia2.repositorios.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;

@Log
@Component
@Primary
class UsuarioNegocioImpl implements UsuarioNegocio {
	private static final String CLIENTE = "cliente";

	private WebSecurityConfig security;
	private ModelMapper mapper;

	protected ProductoRepository repoProducto;
	private ClienteRepository repoCliente;
	private FacturaRepository repoFactura;

	private UsuarioRepository repoUsuario;

	public UsuarioNegocioImpl(WebSecurityConfig security, FacturaRepository repoFactura, ClienteRepository repoCliente,
			UsuarioRepository repoUsuario, ProductoRepository repoProducto, ModelMapper mapper) {
		this.repoCliente = repoCliente;
		this.repoUsuario = repoUsuario;
		this.repoProducto = repoProducto;
		this.repoFactura = repoFactura;
		this.mapper = mapper;
		this.security = security;
	}

	@Override
	public Iterable<Producto> listadoProductos() {
		return repoProducto.findAll();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return repoProducto.findById(id).orElse(null);
	}

	@Override
	public Usuario loguear(String email, String password) {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

	@Override
	public long cuantosUsuariosHay() {
		throw new LogicaNegocioException("NO IMPLEMENTADO");
	}

	@Override
	public long cuantosProductosHay() {
		return repoProducto.count();
	}

	@Override
	public Carrito agregarProductoACarrito(Long id, Carrito carrito) {
		Producto producto = detalleProducto(id);

		if (producto == null) {
			throw new EntityNotFoundException("No se ha encontrado el producto " + id);
		}

		agregarProductoACarrito(producto, carrito);

		return carrito;
	}

	@Override
	public Carrito agregarProductoACarrito(Producto producto, Carrito carrito) {
		Producto existente = carrito.getProducto(producto.getId());

		if (existente == null) {
			producto.setUnidades(1);
			carrito.addProducto(producto);
		} else {
			existente.setUnidades(existente.getUnidades() + 1);
		}

		log.info("Se ha agregado el producto " + producto + " a un carrito");

		return carrito;
	}

	@Override
	public Carrito quitarProductoDeCarrito(Long id, Carrito carrito) {
		Producto producto = carrito.getProducto(id);

		if (producto == null) {
			throw new LogicaNegocioException("No existe el producto a eliminar");
		} else {
			carrito.removeProducto(id);
		}

		log.info("Se ha quitado el producto " + producto + " de un carrito");

		return carrito;
	}

	@Override
	public Carrito quitarProductoDeCarrito(Producto producto, Carrito carrito) {
		return quitarProductoDeCarrito(producto.getId(), carrito);
	}

	@Override
	public Carrito quitarUnidadDeProductoDeCarrito(Long id, Carrito carrito) {
		Integer unidades = carrito.getProducto(id).getUnidades();

		Optional<Producto> productoAlmacen = repoProducto.findById(id);

		if (productoAlmacen.isEmpty() || unidades == 1) {
			quitarProductoDeCarrito(id, carrito);
			return carrito;
		}

		carrito.updateUnidades(id, unidades - 1);

		return carrito;
	}

	@Override
	public Carrito agregarUnidadDeProductoDeCarrito(Long id, Carrito carrito) {
		Integer unidades = carrito.getProducto(id).getUnidades();

		Optional<Producto> productoAlmacen = repoProducto.findById(id);

		if (productoAlmacen.isEmpty()) {
			quitarProductoDeCarrito(id, carrito);
			return carrito;
		}

		if (unidades + 1 <= productoAlmacen.get().getUnidades()) {
			carrito.updateUnidades(id, unidades + 1);
		}

		return carrito;
	}

	@Override
	public String nuevoNumeroFactura(final String anno) {
		final String numeroFacturaObtenida = repoFactura.ultimoNumeroFactura(anno);

		if (numeroFacturaObtenida == null) {
			return anno + "0001";
		}

		String numero = numeroFacturaObtenida.substring(4);

		return anno + String.format("%04d", Integer.parseInt(numero) + 1);
	}

	@Override
	public Factura crearFacturaProForma(String email, Carrito carrito) {
		String anno = String.valueOf(LocalDate.now().getYear());
		String numero = nuevoNumeroFactura(anno);
		Cliente cliente = repoCliente.findByEmail(email);

		Factura factura = mapper.map(carrito, Factura.class);

		factura.setNumero(numero);
		factura.setFecha(LocalDate.now());
		factura.setCliente(cliente);

		return factura;
	}

	@Override
	public synchronized Factura facturar(Factura factura) {
		factura.setNumero(nuevoNumeroFactura(String.valueOf(LocalDate.now().getYear())));

		repoFactura.save(factura);

		return factura;
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		try {
			usuario.setPassword(security.passwordEncoder().encode(usuario.getPassword()));
			usuario.setRol(Rol.USUARIO);

			if (usuario instanceof Cliente cliente) {
				if (cliente.getDni() == null || cliente.getDni().isBlank()) {
					Usuario u = new Usuario();
					mapper.map(cliente, u);
					repoUsuario.save(u);
				} else {
					usuario.setRol(Rol.CLIENTE);
					repoCliente.save(cliente);
				}
			} else {
				repoUsuario.save(usuario);
			}

			return usuario;
		} catch (DataIntegrityViolationException e) {
			String dato = e.getMessage().split("'")[1];

			if (dato.equals(usuario.getEmail())) {
				throw new ClaveDuplicadaException("el email ya está registrado", CLIENTE, "email", e);
			} else if (usuario instanceof Cliente cliente && dato.equals(cliente.getDni())) {
				throw new ClaveDuplicadaException("ese dni ya está registrado", CLIENTE, "dni", e);
			} else {
				throw new ClaveDuplicadaException("hay un campo duplicado", CLIENTE, null, e);
			}
		} catch(Exception e) {
			throw e;
		}
	}
}
