<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.amazonia.entidades.Producto"%>

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
			<%
			for (Producto p : (Iterable<Producto>) request.getAttribute("productos")) {
			%>
			<tr>
				<th><a href="detalle?id=<%=p.getId()%>"><%=p.getId()%></a></th>
				<td><%=p.getNombre()%></td>
				<td><%=p.getPrecio()%></td>
				<td><%=p.getFechaCaducidad()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>
