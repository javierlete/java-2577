<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main class="container">

	<%-- <ul>
		<c:forEach items="${productos}" var="p">
			<li>${p}</li>
		</c:forEach>
	</ul> --%>


	<c:choose>
		<c:when test="${productos.size() > 0}">
			<div
				class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
				<c:forEach items="${productos}" var="p">
					<div class="col">
						<div class="card h-100">
							<img src="https://picsum.photos/300/200?${p.id}"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">${p.nombre}</h5>
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><fmt:formatNumber type="currency" value="${p.precio}" /> </li>
									<li class="list-group-item">${p.unidades == null ? 'No disponible' : p.unidades += ' unidades'}</li>
								</ul>
								<a class="btn btn-primary w-100 stretched-link"
									href="detalle?id=${p.id}">Ver detalle</a>
							</div>
							<div class="card-footer">
								<small class="text-body-secondary"><javatime:format value="${p.fechaCaducidad}" pattern="d' de 'MMMM' del 'YYYY" /></small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p class="text-center fw-bold lead">No hay productos</p>
		</c:otherwise>
	</c:choose>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
