<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/admin-cabecera.jsp"%>

<main class="container">

	<pre>
${producto}

${errores}
	</pre>

	<form action="admin/detalle" method="post">
		<div class="row mb-3">
			<label for="id" class="col-sm-2 col-form-label">Id</label>
			<div class="col-sm">
				<input type="number" readonly class="form-control  ${errores.id != null ? 'is-invalid' : 'is-valid' }" id="id" name="id"
					value="${producto.id}">
				<div class="invalid-feedback">${errores.id}</div>
			</div>
		</div>

		<div class="row mb-3">
			<label for="codigo-barras" class="col-sm-2 col-form-label">Código
				de barras</label>
			<div class="col-sm">
				<input type="number" class="form-control  ${errores.codigoBarras != null ? 'is-invalid' : 'is-valid' }" id="codigo-barras"
					name="codigo-barras" value="${producto.codigoBarras}">
				<div class="invalid-feedback">${errores.codigoBarras}</div>
			</div>
		</div>

		<div class="row mb-3">
			<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
			<div class="col-sm">
				<input type="text"
					class="form-control  ${errores.nombre != null ? 'is-invalid' : 'is-valid' }"
					id="nombre" name="nombre" value="${producto.nombre}">
				<div class="invalid-feedback">${errores.nombre}</div>
			</div>
		</div>

		<div class="row mb-3">
			<label for="precio" class="col-sm-2 col-form-label">Precio</label>
			<div class="col-sm">
				<input type="number" step=".01" class="form-control  ${errores.precio != null ? 'is-invalid' : 'is-valid' }" id="precio"
					name="precio" value="${producto.precio}">
				<div class="invalid-feedback">${errores.precio}</div>
			</div>
		</div>

		<div class="row mb-3">
			<label for="fecha-caducidad" class="col-sm-2 col-form-label">Fecha
				de caducidad</label>
			<div class="col-sm">
				<input type="date" class="form-control  ${errores.fechaCaducidad != null ? 'is-invalid' : 'is-valid' }" id="fecha-caducidad"
					name="fecha-caducidad" value="${producto.fechaCaducidad}">
				<div class="invalid-feedback">${errores.fechaCaducidad}</div>
			</div>
		</div>
		<div class="row mb-3">
			<label for="unidades" class="col-sm-2 col-form-label">Unidades</label>
			<div class="col-sm">
				<input type="number" class="form-control  ${errores.unidades != null ? 'is-invalid' : 'is-valid' }" id="unidades"
					name="unidades" value="${producto.unidades}">
				<div class="invalid-feedback">${errores.unidades}</div>
			</div>
		</div>

		<div class="row mb-3">
			<div class="offset-sm-2 col-sm">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="reset" class="btn btn-warning">Restaurar
					valores originales</button>
				<a class="btn btn-danger" href="admin/detalle">Vaciar formulario</a>
				<div class="text-danger">${errores.general}</div>
			</div>
		</div>
	</form>

</main>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
