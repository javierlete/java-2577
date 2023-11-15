package com.amazonia.presentacion.backend.controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia.accesodatos.DaoProducto;
import com.amazonia.accesodatos.DaoProductoMemoria;
import com.amazonia.entidades.Producto;
import com.amazonia.logicanegocio.UsuarioNegocio;
import com.amazonia.logicanegocio.UsuarioNegocioImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final UsuarioNegocio un = new UsuarioNegocioImpl();

	static {
		DaoProducto dao = new DaoProductoMemoria();

		dao.insertar(new Producto());
		dao.insertar(new Producto("Producto prueba"));
		dao.insertar(new Producto("Otro producto", new BigDecimal("1234.56")));
		dao.insertar(new Producto("Un producto más", new BigDecimal("1234.56"), LocalDate.of(2025, 1, 1)));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("productos", un.listadoProductos());
		request.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
