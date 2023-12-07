<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main class="container">

	<h2>Carrito</h2>

	<c:choose>
		<c:when test="${carrito.productos.size() > 0}">
			<form action="carrito" method="post">
				<table id="carrito" class="table table-borderless table-striped">
					<thead class="table-secondary">
						<tr>
							<th>Nombre</th>
							<th class="text-end">Precio</th>
							<th>Fecha de caducidad</th>
							<th class="text-center">Unidades</th>
							<th class="text-center">OPCIONES</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${carrito.productos}" var="p">
							<tr>
								<td>${p.nombre}</td>
								<td class="text-end"><fmt:formatNumber type="currency"
										value="${p.precio}" /></td>
								<td><javatime:format value="${p.fechaCaducidad}"
										pattern="d' de 'MMMM' del 'YYYY" /></td>
								<td><input type="hidden" name="id" value="${p.id}">
									<div class="input-group mx-auto" style="width: 10rem">
										<button class="menos btn btn-secondary" type="button">
											<i class="bi bi-dash-lg"></i>
										</button>
										<input class="form-control text-end" min="0" maxlength="3"
											type="number" name="unidades" value="${p.unidades}">
										<button class="mas btn btn-secondary" type="button">
											<i class="bi bi-plus-lg"></i>
										</button>
									</div></td>
								<td class="text-center"><a
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
							<td class="text-center">
								<button class="btn btn-sm btn-primary">Volver</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<p class="lead">No hay productos en el carrito</p>
		</c:otherwise>
	</c:choose>
</main>

<script src="js/carrito.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
