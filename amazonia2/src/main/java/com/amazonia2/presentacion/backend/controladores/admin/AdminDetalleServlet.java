package com.amazonia2.presentacion.backend.controladores.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Level;

import com.amazonia2.bibliotecas.validaciones.GestionErrores;
import com.amazonia2.entidades.Producto;
import com.amazonia2.globales.Global;
import com.amazonia2.logicanegocio.ModificacionDeBorradoLogicaNegocioException;
import com.amazonia2.logicanegocio.ModificacionDeModificadoLogicaNegocioException;

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

		request.setAttribute("noValidar", true);

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

		String sVersion = request.getParameter("version");

		Long id = sId.trim().length() == 0 ? null : Long.parseLong(sId);
		BigDecimal precio = sPrecio.trim().length() == 0 ? null : new BigDecimal(sPrecio);
		LocalDate fechaCaducidad = sFechaCaducidad.trim().length() == 0 ? null : LocalDate.parse(sFechaCaducidad);
		Integer unidades = sUnidades.trim().length() == 0 ? null : Integer.valueOf(sUnidades);
		java.sql.Timestamp version = sVersion.trim().length() == 0 ? null
				: new java.sql.Timestamp(Long.parseLong(sVersion));

		Producto producto = Producto.builder().id(id).codigoBarras(codigoBarras).nombre(nombre).precio(precio)
				.fechaCaducidad(fechaCaducidad).unidades(unidades).version(version).build();

		var validaciones = validator.validate(producto);

		var conversor = new GestionErrores<Producto>();

		var errores = conversor.convertirAErrores(validaciones);

		if (errores.size() == 0) {
			try {
				if (producto.getId() != null) {
					try {
						Global.AN.modificarProducto(producto);
					} catch (ModificacionDeModificadoLogicaNegocioException e) {
						log.log(Level.WARNING, "Error de concurrencia", e);

						Producto productoActual = Global.AN.detalleProducto(id);

//						errores.put("general",
//								"El registro a modificar, ha sido cambiado por otro administrador. Por favor, revise la información de nuevo para modificarla de nuevo.");

						request.setAttribute("producto", productoActual);
						request.setAttribute("errores", errores);

						request.setAttribute("alerta",
								"El registro a modificar ha sido cambiado por otro administrador. Por favor, revise la información de nuevo para modificarla de nuevo.");
						request.setAttribute("nivelAlerta", "warning");

						request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request,
								response);
						return;
					} catch (ModificacionDeBorradoLogicaNegocioException e) {
						log.log(Level.WARNING, "Error de concurrencia", e);

//						errores.put("general",
//								"El registro a modificar, ha sido borrado por otro administrador. Si quieres insertarlo, guarda los datos, si no, cancela el proceso");
						request.setAttribute("alerta",
								"El registro a modificar, ha sido borrado por otro administrador. Si quieres insertarlo, guarda los datos, si no, cancela el proceso");
						request.setAttribute("nivelAlerta", "warning");

						producto.setId(null);

						request.setAttribute("producto", producto);
						request.setAttribute("errores", errores);

						request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request,
								response);
						return;
					}
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

			request.setAttribute("alerta", "Revisa la información del formulario. Tienes errores de validación.");
			request.setAttribute("nivelAlerta", "danger");

			request.getRequestDispatcher("/WEB-INF/vistas/admin/admin-detalle.jsp").forward(request, response);
		}

	}
}
