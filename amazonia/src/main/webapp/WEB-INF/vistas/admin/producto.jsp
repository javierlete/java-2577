<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.amazonia.entidades.Producto"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de producto</title>
</head>
<body>

	<form action="producto" method="post">
		<div>
			<label for="id">Id</label> <input readonly name="id" id="id"
				value="${producto.id}">
		</div>
		<div>
			<label for="nombre">Nombre</label> <input name="nombre" id="nombre"
				value="${producto.nombre}">
		</div>
		<div>
			<label for="precio">Precio</label> <input type="number" step=".01"
				min="0" name="precio" id="precio" value="${producto.precio}">
		</div>
		<div>
			<label for="fecha">Fecha de caducidad</label> <input name="fecha"
				id="fecha" type="date" value="${producto.fechaCaducidad}">
		</div>
		<div>
			<button>Guardar</button>
		</div>
	</form>

</body>
</html>
