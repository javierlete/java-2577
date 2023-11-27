package com.amazonia2.presentacion.backend.controladores;

import java.io.IOException;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.globales.Global;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String borrar = request.getParameter("borrar");
		String sId = request.getParameter("id");
		
		if(borrar == null && sId == null) {
			request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
			return;
		}
		
		Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
		
		Long id = Long.parseLong(sId);
		
		if(borrar == null) {
			carrito.addProducto(Global.UN.detalleProducto(id));
		} else {
			carrito.removeProducto(id);
		}

		response.sendRedirect(request.getContextPath() + "/carrito");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
