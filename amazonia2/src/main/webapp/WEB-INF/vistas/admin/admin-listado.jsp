<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/admin-cabecera.jsp"%>

<main class="container">

	<table class="table table-bordered table-striped table-hover">
		<thead class="table-dark">
			<tr>
				<th class="text-end">Id</th>
				<th>Código de barras</th>
				<th>Nombre</th>
				<th class="text-end">Precio</th>
				<th>Fecha de caducidad</th>
				<th class="text-end">Unidades</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>

			<c:choose>
				<c:when test="${productos.size() > 0}">
					<c:forEach items="${productos}" var="p">
						<tr>
							<th class="text-end">${p.id}</th>
							<td>${p.codigoBarras}</td>
							<td>${p.nombre}</td>
							<td class="text-end"><fmt:formatNumber value="${p.precio}" type="currency"/></td>
							<td>${p.fechaCaducidad == null ? '<i class="bi bi-dash-lg"></i>' : p.fechaCaducidad }</td>
							<td class="text-end">${p.unidades}</td>
							<td><a class="btn btn-sm btn-primary"
								href="admin/detalle?id=${p.id}">Editar</a> <a
								onclick="return confirm('¿Estás seguro de borrar el producto ${p.nombre}?')"
								class="btn btn-sm btn-danger" href="admin/borrar?id=${p.id}">Borrar</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" class="text-center fw-bold lead">
							No hay productos
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot class="table-dark">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a class="btn btn-sm btn-primary" href="admin/detalle">Añadir</a>
				</td>
			</tr>
		</tfoot>
	</table>

</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
