<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<fmt:setLocale value="es-ES" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Listado de productos</title>

<base href="${pageContext.request.contextPath}/">

<link rel="stylesheet" href="css/bootstrap-icons.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/amazonia2.css" rel="stylesheet">

<script src="js/bootstrap.bundle.min.js"></script>

<link rel="icon" type="image/svg+xml" href="imgs/shop.svg">

<!-- DataTables -->
<link rel="stylesheet" href="css/dataTables.bootstrap5.min.css">
<script src="js/jquery-3.7.0.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap5.min.js"></script>
<script>
	$(function() {
		new DataTable('table', {
			language : {
				url : 'json/es-ES.json',
			}
		})
	});
</script>

</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark sticky-top"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Amazonia2</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link" href="listado">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-sm-0">
					<c:if test="${sessionScope.usuario.rol.id == 1}">
						<li class="nav-item"><a class="nav-link" href="admin/listado">Administración</a></li>
					</c:if>
					<li class="navbar-text">${sessionScope.usuario.nombre}</li>

					<li class="nav-item"><a class="nav-link" href="carrito"><i
							class="bi bi-cart4"></i>${carrito.numeroProductos > 0 ? '(' += carrito.numeroProductos += ')' : ''}</a></li>
					<c:choose>
						<c:when test="${sessionScope.usuario == null}">
							<li class="nav-item"><a class="nav-link" href="login">Iniciar
									sesión</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="logout">Cerrar
									sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

			</div>
		</div>
	</nav>
	<c:if test="${alerta != null}">
		<div class="alert alert-${nivelAlerta} alert-dismissible fade show"
			role="alert">
			${alerta}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<%
	session.removeAttribute("alerta");
	session.removeAttribute("nivelAlerta");
	%>