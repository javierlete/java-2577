<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Listado de productos</title>
<link rel="stylesheet" href="css/bootstrap-icons.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark sticky-top mb-4"
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
					<li class="nav-item"><a class="nav-link" href="#">Principal</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<main class="container">

		<%-- <ul>
		<c:forEach items="${productos}" var="p">
			<li>${p}</li>
		</c:forEach>
	</ul> --%>

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
								<li class="list-group-item">${p.precio}€</li>
								<li class="list-group-item">${p.unidades == null ? 'No disponible' : p.unidades += ' unidades'}</li>
							</ul>
						</div>
						<div class="card-footer">
							<small class="text-body-secondary">${p.fechaCaducidad}</small>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</main>

	<footer class="position-absolute bottom-0 w-100 text-bg-dark p-2 mt-4 d-flex justify-content-between">
		<p>&copy;2023 Javier Lete</p>
		<ul class="list-unstyled d-flex">
			<li><a class="text-white p-1" href="#"><i
					class="bi bi-facebook"></i></a></li>
			<li><a class="text-white p-1" href="#"><i
					class="bi bi-twitter-x"></i></a></li>
			<li><a class="text-white p-1" href="#"><i
					class="bi bi-instagram"></i></a></li>
			<li><a class="text-white p-1" href="#"><i
					class="bi bi-youtube"></i></a></li>
			<li><a class="text-white p-1" href="#"><i
					class="bi bi-tiktok"></i></a></li>
		</ul>
	</footer>

</body>
</html>
