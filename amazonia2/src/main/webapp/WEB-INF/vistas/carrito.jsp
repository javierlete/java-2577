<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>


<main class="container">

	<h2>Carrito</h2>
	<table class="table">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Fecha de caducidad</th>
				<th>Unidades</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${carrito.productos}" var="p">
				<tr>
					<td>${p.nombre}</td>
					<td>${p.precio}</td>
					<td>${p.fechaCaducidad}</td>
					<td>${p.unidades}</td>
					<td>
						<a onclick="return confirm('¿Estás seguro de borrar el producto ${p.nombre}?')" class="btn btn-sm btn-danger" href="carrito?id=${p.id}&borrar">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<a class="btn btn-sm btn-primary" href="listado">Volver</a>
				</td>
			</tr>
		</tfoot>
	</table>

</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
