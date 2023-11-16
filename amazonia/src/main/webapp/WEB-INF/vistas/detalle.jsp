<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.amazonia.entidades.Producto"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle de producto</title>
</head>
<body>

<pre>${producto.nombre}</pre>
<pre><%=request.getAttribute("producto") == null ? "" : ((com.amazonia.entidades.Producto)request.getAttribute("producto")).getNombre() %></pre>

<%-- 	<c:if test="${producto != null}">
		<dl>
			<dt>Id</dt>
			<dd>${producto.id}</dd>
			<dt>Nombre</dt>
			<dd>${producto.nombre}</dd>
			<dt>Precio</dt>
			<dd>${producto.precio}</dd>
			<dt>Fecha de caducidad</dt>
			<dd>${producto.fechaCaducidad}</dd>
		</dl>
	</c:if>
	<c:if test="${producto == null}">
		<h1>No se ha encontrado el producto</h1>
	</c:if> --%>

	<c:choose>
		<c:when test="${producto != null}">
			<dl>
				<dt>Id</dt>
				<dd>${producto.id}</dd>
				<dt>Nombre</dt>
				<dd>${producto.nombre}</dd>
				<dt>Precio</dt>
				<dd>${producto.precio}</dd>
				<dt>Fecha de caducidad</dt>
				<dd>${producto.fechaCaducidad}</dd>
			</dl>
		</c:when>
		<c:otherwise>
			<h1>No se ha encontrado el producto</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>
