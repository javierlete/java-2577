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

@WebServlet("/detalle")
public class DetalleServlet extends HttpServlet {
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
		String sId = request.getParameter("id");
		Long id = Long.parseLong(sId);
		
		request.setAttribute("producto", un.datosProducto(id));
		request.getRequestDispatcher("/WEB-INF/vistas/detalle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
