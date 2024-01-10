const URL_REST = 'http://localhost:8080/api/v2';
const URL_USUARIO = URL_REST + '/negocio/usuario';
const URL_PRODUCTOS = URL_USUARIO + '/productos';

$(function() {
    $('#alerta').hide();

    mostrar('index');

    cargarProductos();
});

function cargarProductos() {
    $.getJSON(URL_PRODUCTOS, function(productos) {
        $.each(productos, function(clave, p) {
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
                                    <span>${p.precio}</span>
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
    $.getJSON(URL_PRODUCTOS + '/' + id, function(producto) {
        mostrar('detalle');
        console.log(producto);

        $('#detalle-nombre').html(producto.nombre);
        $('#detalle-fecha-caducidad').html(producto.fechaCaducidad);
        $('#detalle-precio').html(producto.precio);
        $('#detalle-unidades').html(producto.unidades);
        $('#detalle-codigo-barras').html(producto.codigoBarras);

    });
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
        language : {
            url : 'json/es-ES.json',
        }
    });
}
