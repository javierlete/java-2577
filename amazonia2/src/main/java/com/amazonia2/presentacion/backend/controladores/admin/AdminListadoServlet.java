package com.amazonia2.presentacion.backend.controladores.admin;

import java.io.IOException;

import com.amazonia2.globales.Global;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/listado")
public class AdminListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var productos = Global.AN.listadoProductos();
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-listado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
