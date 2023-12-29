'use strict';

const URL = '/api/v2/negocio/usuario/carrito';

const moneda = new Intl.NumberFormat('es-ES', {
	style: 'currency',
	currency: 'EUR',
	useGrouping: true
});

async function quitarUnidad(id) {
	cambiarUnidad(id, 'quitar');
}

async function agregarUnidad(id) {
	cambiarUnidad(id, 'agregar');
}

async function cambiarUnidad(id, accion) {
	console.log(accion + 'Unidad', id);

	const respuesta = await fetch(`${URL}/${id}/${accion}-unidad`);

	if (respuesta.ok) {
		const carrito = await respuesta.json();

		const numeroProductosEnCarrito = carrito.productos.length;

		document.querySelector('#numero-productos-carrito').innerText = numeroProductosEnCarrito;

		const totalGlobal = carrito.total;

		const productos = carrito.productos.filter(p => p.id === id);

		document.querySelector('#total-global').innerText = moneda.format(totalGlobal);

		if (!productos.length) {
			document.querySelector(`#tr-${id}`).remove();
			return;
		}

		const total = productos[0].total;
		const unidades = productos[0].unidades;

		console.log(carrito);

		document.querySelector('#input-' + id).value = unidades;
		document.querySelector('#total-' + id).innerText = moneda.format(total);
	} else {
		alert(`No se ha podido ${accion} la unidad`);
	}
}
