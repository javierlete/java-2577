package com.amazonia.presentacion.backend.controladores;

import java.io.IOException;

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
