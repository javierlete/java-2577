<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.amazonia.entidades.Producto"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de productos</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Fecha de caducidad</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="p">
				<tr>
					<th><a href="detalle?id=${p.id}">${p.id}</a></th>
					<td>${p.nombre}</td>
					<td>${p.precio}</td>
					<td>${p.fechaCaducidad}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
