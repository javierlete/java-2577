package com.amazonia2.presentacion.backend.controladores;

import java.io.IOException;

import com.amazonia2.entidades.Carrito;
import com.amazonia2.entidades.Producto;
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
			Producto producto = Global.UN.detalleProducto(id);
			Integer unidades = 1;
			
			if(carrito.getProducto(id) != null) {
				unidades += carrito.getProducto(id).getUnidades();
			}
			
			producto.setUnidades(unidades);
			carrito.addProducto(producto);
		} else {
			carrito.removeProducto(id);
		}

		response.sendRedirect(request.getContextPath() + "/carrito");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("id");
		String[] unidades = request.getParameterValues("unidades");
		
		if(ids == null || unidades == null) {
			response.sendRedirect(request.getContextPath() + "/listado");
			return;
		}
		
		Carrito carrito = (Carrito) request.getSession().getAttribute("carrito");
		
		Long id;
		Integer u;
		
		for(int i = 0; i < ids.length; i++) {
			id = Long.parseLong(ids[i]);
			u = Integer.parseInt(unidades[i]);
			
			if(u > 0) {
				carrito.getProducto(id).setUnidades(u);
			} else {
				carrito.removeProducto(id);
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/listado");
	}

}
