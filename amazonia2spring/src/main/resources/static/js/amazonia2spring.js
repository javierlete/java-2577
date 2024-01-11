const URL_BASE = location.href.replace('/index.html','');
const URL_REST = URL_BASE + '/api/v2';
const URL_USUARIO = URL_REST + '/negocio/usuario';
const URL_PRODUCTOS = URL_USUARIO + '/productos';
const URL_ADMIN = URL_REST + '/negocio/admin/productos';

let carrito = JSON.parse(sessionStorage.getItem('carrito'));

if (!carrito) {
    carrito = [];
    guardarCarrito();
}

$(function () {
    $('#numero-productos-carrito').html('(' + carrito.length + ')');
    $('#alerta').hide();

    $('#login form:first-of-type').on('submit', login);

    // cargarIndex();
    cargarLogin();
});

function cargarProductos() {
    $.getJSON(URL_PRODUCTOS, function (productos) {
        $('#index > div').empty();

        $.each(productos, function (clave, p) {
            $('#index > div').append(`
                <div class="col">
                    <div class="card h-100">
                        <img src="https://picsum.photos/400/300?${p.id}"
                            class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${p.nombre}</h5>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between">
                                    <span class="fw-bold">Precio:</span>
                                    <span>${moneda.format(p.precio)}</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span class="fw-bold">Unidades:</span>
                                    <span>${p.unidades} u</span>
                                </li>
                            </ul>
                            
                        </div>
                        <div class="card-footer">
                            <div class="text-center">
                                <a class="btn btn-primary fs-5" href="javascript:agregarACarrito(${p.id})"><i class="bi bi-bag-plus-fill"></i></a>
                                <a class="btn btn-secondary fs-5" href="javascript:verDetalle(${p.id})"><i class="bi bi-search"></i></a>
                            </div>
                        </div>
                    </div>
                </div>`);
        });
    });

}
function verDetalle(id) {
    $.getJSON(URL_PRODUCTOS + '/' + id, function (producto) {
        mostrar('detalle');
        console.log(producto);

        $('#detalle-nombre').html(producto.nombre);
        $('#detalle-fecha-caducidad').html(producto.fechaCaducidad);
        $('#detalle-precio').html(moneda.format(producto.precio));
        $('#detalle-unidades').html(producto.unidades);
        $('#detalle-codigo-barras').html(producto.codigoBarras);

        $('#detalle-agregar-carrito').on('click', function (e) {
            e.preventDefault();
            agregarACarrito(producto);
        });
    });
}

function agregarACarrito(idOProducto) {
    if (typeof idOProducto === 'number') {
        const id = idOProducto;
        $.getJSON(URL_PRODUCTOS + '/' + id, agregarProductoACarrito);
    } else {
        const producto = idOProducto;
        agregarProductoACarrito(producto);
    }
}

function agregarProductoACarrito(producto) {
    const productoEnCarrito = carrito.filter(p => p.id === producto.id)[0];

    if (productoEnCarrito) {
        productoEnCarrito.unidades++;
    } else {
        producto.unidades = 1;
        carrito.push(producto);
    }

    cargarCarrito();
}

function cargarCarrito() {
    guardarCarrito();

    mostrar('carrito');

    $('#carrito tbody').empty()

    let totalGlobal = 0;

    $.each(carrito, function (clave, p) {
        const total = p.precio * p.unidades;
        totalGlobal += total;

        $('#carrito tbody').append(`
            <tr id="${p.id}">
                <td>${p.nombre}</td>
                <td class="text-end">${moneda.format(p.precio)}</td>
                <td class="d-flex justify-content-center">
                    <div class="input-group mb-3" style="min-width: 11rem; max-width: 11rem">
                        <button class="btn btn-outline-secondary" type="button"
                            th:onclick="|quitarUnidad(${p.id})|">
                            <i class="bi bi-dash-lg"></i>
                        </button>
                        <input th:id="|input-${p.id}|" type="text" class="form-control text-end"
                            placeholder="" aria-label="Example text with button addon"
                            aria-describedby="button-addon1" value="${p.unidades}">
                        <button class="btn btn-outline-secondary" type="button"
                            th:onclick="|agregarUnidad(${p.id})|">
                            <i class="bi bi-plus-lg"></i>
                        </button>
                    </div>
                </td>
                <td th:id="|total-${p.id}|" class="text-end">${moneda.format(total)}</td>
                <td><a onclick="return confirm('¿Estás seguro?')" class="btn btn-sm btn-outline-danger"
                        href="javascript:carritoBorrar(${p.id})"><i
                            class="bi bi-trash-fill"></i></a></td>
            </tr>`);
    });

    $('#total-global').html(moneda.format(totalGlobal));
}

function carritoBorrar(id) {
    carrito = carrito.filter(p => p.id !== id);
    cargarCarrito();
}

function cargarIndex() {
    mostrar('index');
    cargarProductos();
}

function mostrar(capa) {
    ocultarTodo();
    $('#' + capa).show();
}

function ocultarTodo() {
    $('main > section').hide();
}

function activarDatatables() {
    DataTable('.table-bordered', {
        language: {
            url: 'json/es-ES.json',
        }
    });
}

function guardarCarrito() {
    $('#numero-productos-carrito').html('(' + carrito.length + ')');
    sessionStorage.setItem('carrito', JSON.stringify(carrito));
}

function cargarAdmin() {
    mostrar('admin');

    $.getJSON(URL_ADMIN, function (productos) {
        $('#admin tbody').empty();
        $.each(productos, function (clave, p) {
            $('#admin tbody').append(`
                <tr>
                    <td class="text-end">${p.id}</td>
                    <td class="text-center">${p.codigoBarras}</td>
                    <td>${p.nombre}</td>
                    <td class="text-end">${moneda.format(p.precio)}</td>
                    <td class="text-center">${p.fechaCaducidad ? p.fechaCaducidad : ''}</td>
                    <td class="text-end">${p.unidades}</td>
                    <td>
                        <a class="btn btn-sm btn-primary" href="javascript:cargarDetalle(${p.id})">Editar</a>
                        <a onclick="return confirm('¿Estás seguro?')" class="btn btn-sm btn-danger" href="javascript:borrar(${p.id})">Borrar</a>
                    </td>
                </tr>`);
        });
    });
}

function borrar(id) {
    $.ajax({
        url: URL_ADMIN + '/' + id,
        type: 'DELETE',
        success: function (result) {
            cargarAdmin();
        },
        error: function (xhr, status, error) {
            console.log('Error:', error);
        }
    });
}

function cargarDetalle(id) {
    if (id) {
        $.getJSON(URL_ADMIN + '/' + id, function (p) {
            $('#id').val(p.id);
            $('#nombre').val(p.nombre);
            $('#fecha-caducidad').val(p.fechaCaducidad);
            $('#precio').val(p.precio);
            $('#unidades').val(p.unidades);
            $('#codigo-barras').val(p.codigoBarras);
        });
    } else {
        $('#id').val('');
        $('#nombre').val('');
        $('#fecha-caducidad').val('');
        $('#precio').val('');
        $('#unidades').val('');
        $('#codigo-barras').val('');
    }

    mostrar('form');
}

function cancelar() {
    cargarAdmin();
}

function guardar() {
    const producto = {
        id: $('#id').val(),
        nombre: $('#nombre').val(),
        fechaCaducidad: $('#fecha-caducidad').val(),
        precio: $('#precio').val(),
        unidades: $('#unidades').val(),
        codigoBarras: $('#codigo-barras').val()
    };

    const tipo = producto.id ? 'PUT' : 'POST';
    const url = URL_ADMIN + (tipo === 'PUT' ? '/' + producto.id : '');

    $.ajax({
        url: url,
        type: tipo,
        data: JSON.stringify(producto),
        contentType: 'application/json',
        success: function (result) {
            cargarAdmin();
        },
        error: function (xhr, status, error) {
            console.log('Error:', error);
        }
    })
}

function cargarPagar() {
    mostrar('pagar');

    let totalGlobal = 0;

    $.each(carrito, function (clave, p) {
        const total = p.precio * p.unidades;
        totalGlobal += total;

        $('#pagar tbody').append(`
        <tr>
            <td>${p.nombre}</td>
            <td class="text-end">${moneda.format(p.precio)}</td>
            <td class="text-end">${p.unidades}</td>
            <td class="text-end">${moneda.format(total)}</td>
        </tr>
        `);
    });

    $('#pagar-total-global').html(moneda.format(totalGlobal));
}

function cargarLogin() {
    mostrar('login');
}

function login(e) {
    e.preventDefault();
    
    const usuario = {
        username: $('#username').val(),
        password: $('#password').val()
    };

    console.log(usuario);
}