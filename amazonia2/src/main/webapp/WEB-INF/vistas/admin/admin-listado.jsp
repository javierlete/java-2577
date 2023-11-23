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
				</tr>
			</c:forEach>
		</tbody>
	</table>

</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
