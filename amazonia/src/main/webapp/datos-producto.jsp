<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.amazonia.logicanegocio.*, com.amazonia.accesodatos.*, com.amazonia.entidades.*, java.math.*, java.time.*"%>
<%!private static final UsuarioNegocio un = new UsuarioNegocioImpl();

	static {
		DaoProducto dao = new DaoProductoMemoria();

		dao.borrar(1L);
		dao.borrar(2L);
		dao.borrar(3L);
		dao.borrar(4L);

		dao.insertar(new Producto());
		dao.insertar(new Producto("Producto prueba"));
		dao.insertar(new Producto("Otro producto", new BigDecimal("1234.56")));
		dao.insertar(new Producto("Un producto más", new BigDecimal("1234.56"), LocalDate.of(2025, 1, 1)));
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos de producto</title>
</head>
<body>

	<%
	String sId = request.getParameter("id");

	Long id = Long.parseLong(sId);
	
	Producto p = un.datosProducto(id);
	%>
	<dl>
		<dt>Id</dt>
		<dd><%= p.getId()%></dd>
		<dt>Nombre</dt>
		<dd><%= p.getNombre()%></dd>
		<dt>Precio</dt>
		<dd><%= p.getPrecio()%></dd>
		<dt>Fecha de caducidad</dt>
		<dd><%= p.getFechaCaducidad()%></dd>
	</dl>
</body>
</html>
