<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main class="container">

	<h2>Carrito</h2>
	<form action="carrito" method="post">
		<table class="table table-borderless table-striped">
			<thead class="table-secondary">
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
						<td>
							<input type="hidden" name="id" value="${p.id}">
							<div class="input-group" style="width: 10rem">
								<button class="menos btn btn-secondary" type="button">
									<i class="bi bi-dash-lg"></i>
								</button>
								<input class="form-control text-end" min="0" maxlength="3"
									type="number" name="unidades" value="${p.unidades}">
								<button class="mas btn btn-secondary" type="button">
									<i class="bi bi-plus-lg"></i>
								</button>
							</div>
						</td>
						<td><a
							onclick="return confirm('¿Estás seguro de borrar el producto ${p.nombre}?')"
							class="btn btn-sm btn-danger" href="carrito?id=${p.id}&borrar">Borrar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot class="table-secondary">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><button class="btn btn-sm btn-primary">Volver</button></td>
				</tr>
			</tfoot>
		</table>
	</form>
</main>

<script src="js/carrito.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
