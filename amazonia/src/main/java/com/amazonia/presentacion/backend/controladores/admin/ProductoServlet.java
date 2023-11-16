package com.amazonia.presentacion.backend.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoMemoria;
import com.amazonia.entidades.Producto;
import com.amazonia.logicanegocio.AdminNegocio;
import com.amazonia.logicanegocio.AdminNegocioImpl;
import com.amazonia.logicanegocio.UsuarioNegocio;
import com.amazonia.logicanegocio.UsuarioNegocioImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/producto")
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final UsuarioNegocio un = new UsuarioNegocioImpl();

	static {
		DaoProducto dao = new DaoProductoMemoria();

		dao.insertar(new Producto());
		dao.insertar(new Producto("Producto prueba"));
		dao.insertar(new Producto("Otro producto", new BigDecimal("1234.56")));
		dao.insertar(new Producto("Un producto más", new BigDecimal("1234.56"), LocalDate.of(2025, 1, 1)));
	}

	private AdminNegocio an = new AdminNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		if (sId != null) {
			Long id = Long.parseLong(sId);
			request.setAttribute("producto", un.datosProducto(id));
		}

		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Recoger los datos de la petición
		String sId = request.getParameter("id"); // name="id"
		String nombre = request.getParameter("nombre"); // name="nombre"
		String sPrecio = request.getParameter("precio");
		String sFecha = request.getParameter("fecha");
		
		// 2. Convertir la información
		Long id = sId.trim().length() == 0 ? null : Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);
		LocalDate fecha = LocalDate.parse(sFecha);
		
		// 3. Convertir en modelo
		Producto producto = new Producto(id, nombre, precio, fecha);
		
		// 4. Ejecutar lógica de negocio
		an.crearProducto(producto);
		
		// 5. Generar información para la vista
		
		
		// 6. Saltar a la vista
		response.sendRedirect(request.getContextPath() + "/listado");
	}

}
