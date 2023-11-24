package com.amazonia2.presentacion.backend.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.amazonia2.entidades.Producto;
import com.amazonia2.globales.Global;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/detalle")
public class AdminDetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		if (sId != null) {
			Long id = Long.parseLong(sId);
			Producto producto = Global.AN.detalleProducto(id);
			request.setAttribute("producto", producto);
		}

		request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");
		String codigoBarras = request.getParameter("codigo-barras");
		String nombre = request.getParameter("nombre");
		String sPrecio = request.getParameter("precio");
		String sFechaCaducidad = request.getParameter("fecha-caducidad");
		String sUnidades = request.getParameter("unidades");

		Long id = sId.trim().length() == 0 ? null : Long.parseLong(sId);
		BigDecimal precio = new BigDecimal(sPrecio);
		LocalDate fechaCaducidad = sFechaCaducidad.trim().length() == 0 ? null : LocalDate.parse(sFechaCaducidad);
		Integer unidades = sUnidades.trim().length() == 0 ? null : Integer.valueOf(sUnidades);

		Producto producto = new Producto(id, codigoBarras, nombre, precio, fechaCaducidad, unidades);
		
		if(producto.getId() != null) {
			Global.AN.modificarProducto(producto);
		} else {
			Global.AN.insertarProducto(producto);
		}
		
//		request.getRequestDispatcher("/admin/listado").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/admin/listado");
	}

}
