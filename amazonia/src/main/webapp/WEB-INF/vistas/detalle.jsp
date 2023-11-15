<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.amazonia.entidades.Producto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle de producto</title>
</head>
<body>
	<%
	Producto p = (Producto) request.getAttribute("producto");
	%>

	<dl>
		<dt>Id</dt>
		<dd><%=p.getId()%></dd>
		<dt>Nombre</dt>
		<dd><%=p.getNombre()%></dd>
		<dt>Precio</dt>
		<dd><%=p.getPrecio()%></dd>
		<dt>Fecha de caducidad</dt>
		<dd><%=p.getFechaCaducidad()%></dd>
	</dl>

</body>
</html>
