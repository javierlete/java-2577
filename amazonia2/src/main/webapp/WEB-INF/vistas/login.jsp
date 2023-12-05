<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<main class="container">
	<c:choose>
		<c:when test="${hayUsuarios}">
			<form action="login" method="post">
				<div class="row mb-3">
					<label for="email" class="col-sm-2 col-form-label">Correo
						electrónico</label>
					<div class="col-sm">
						<input type="email" class="form-control" id="email" name="email"
							value="${param.email}">
					</div>
				</div>

				<div class="row mb-3">
					<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
					<div class="col-sm">
						<input type="password" class="form-control" id="password"
							name="password">
					</div>
				</div>

				<div class="row mb-3">
					<div class="offset-sm-2 col-sm">
						<button type="submit" class="btn btn-primary">Iniciar
							sesión</button>
						<div class="text-danger">${error}</div>
					</div>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<p class="text-center fw-bold lead">No hay usuarios en la aplicación</p>
		</c:otherwise>
	</c:choose>
</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
