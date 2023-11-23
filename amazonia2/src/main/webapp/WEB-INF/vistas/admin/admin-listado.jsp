<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/admin-cabecera.jsp"%>

<main class="container">

	<table class="table table-bordered table-striped table-hover">
		<thead class="table-dark">
			<tr>
				<th>Id</th>
				<th>Código de barras</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Fecha de caducidad</th>
				<th>Unidades</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${productos}" var="p">
				<tr>
					<th>${p.id}</th>
					<td>${p.codigoBarras}</td>
					<td>${p.nombre}</td>
					<td>${p.precio}</td>
					<td>${p.fechaCaducidad}</td>
					<td>${p.unidades}</td>
					<td>
						<a class="btn btn-sm btn-primary" href="admin/detalle?id=${p.id}">Editar</a>
						<a onclick="return confirm('¿Estás seguro de borrar el producto ${p.nombre}?')" class="btn btn-sm btn-danger" href="admin/borrar?id=${p.id}">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot class="table-dark">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<a class="btn btn-sm btn-primary" href="admin/detalle">Añadir</a>
				</td>
			</tr>
		</tfoot>
	</table>

</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
