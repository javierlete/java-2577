package com.amazonia2.presentacion.backend.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;

import com.amazonia2.bibliotecas.validaciones.GestionErrores;
import com.amazonia2.entidades.Producto;
import com.amazonia2.globales.Global;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.java.Log;

@Log
@WebServlet("/admin/detalle")
public class AdminDetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = validatorFactory.getValidator();

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
		BigDecimal precio = sPrecio.trim().length() == 0 ? null : new BigDecimal(sPrecio);
		LocalDate fechaCaducidad = sFechaCaducidad.trim().length() == 0 ? null : LocalDate.parse(sFechaCaducidad);
		Integer unidades = sUnidades.trim().length() == 0 ? null : Integer.valueOf(sUnidades);

		Producto producto = Producto.builder().id(id).codigoBarras(codigoBarras).nombre(nombre).precio(precio)
				.fechaCaducidad(fechaCaducidad).unidades(unidades).build();

		var validaciones = validator.validate(producto);

		var conversor = new GestionErrores<Producto>();

		var errores = conversor.convertirAErrores(validaciones);

		if (errores.size() == 0) {
			try {
				if (producto.getId() != null) {
					Global.AN.modificarProducto(producto);
				} else {
					Global.AN.insertarProducto(producto);
				}
			} catch (Exception e) {
				errores.put("general", "Error no esperado");
				
				log.log(Level.SEVERE, "Error al guardar producto", e);
						
				request.setAttribute("producto", producto);
				request.setAttribute("errores", errores);
				
				request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request, response);
				return;
			}
			// request.getRequestDispatcher("/admin/listado").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/admin/listado");
		} else {
			request.setAttribute("producto", producto);
			request.setAttribute("errores", errores);

			request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request, response);
		}

	}
}
