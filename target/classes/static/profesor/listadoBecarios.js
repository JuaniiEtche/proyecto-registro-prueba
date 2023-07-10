

function cargarTablaBecarios() {
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip + "/admin/personas",
        type: "GET",
        async:false,
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {

            for (let i = 0; i < data.length; i++) {
                // Obtener la referencia a la tabla
                var tabla2 = document.getElementById("tablaBecariosA");

                // Obtener la referencia al cuerpo de la tabla
                var cuerpoTabla2 = tabla2.getElementsByTagName("tbody")[0];

                // Crear una nueva fila
                var nuevaFila = cuerpoTabla2.insertRow();

                // Crear celdas y agregar contenido
                var celdaNombre = nuevaFila.insertCell();
                celdaNombre.textContent = data[i]['nombre'] + " " + data[i]['apellido'];

                var celdaEdad = nuevaFila.insertCell();
                celdaEdad.textContent = data[i]['dni'];

                var celdaEdad = nuevaFila.insertCell();
                celdaEdad.textContent = data[i]['numTelefono'];

                var celdaEdad = nuevaFila.insertCell();
                celdaEdad.textContent = data[i]['email'];

                var celdaEdad = nuevaFila.insertCell();
                celdaEdad.textContent = data[i]['legajo'];

                var celdaBoton2 = nuevaFila.insertCell();

                var boton = document.createElement("button");
                boton.textContent = "Ver asistencias";
                boton.classList.add("btn", "btn-secondary")

                boton.addEventListener("click", function () {
                    mostrarAsistencias(data[i]['idPersona']).then(abrirModal).catch(console.error); // Pasar el ID del becario al mostrar asistencias
                })

                // Agregar el botón a la celda correspondiente
                celdaBoton2.appendChild(boton);

                // Agregar la fila al cuerpo de la tabla
                cuerpoTabla2.appendChild(nuevaFila);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
    $('#tablaBecariosA').DataTable({
        language: {
            url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json'
        },
        columns: [
            null, // Columna 1 - Se permitirá la búsqueda
            { searchable: false }, // Columna 2 - No se permitirá la búsqueda
            { searchable: false }, // Columna 3 - No se permitirá la búsqueda
            { searchable: false }, // Columna 4 - No se permitirá la búsqueda
            { searchable: false }, // Columna 5 - No se permitirá la búsqued
            { searchable: false }, // Columna 6 - No se permitirá la búsqued

        ],
        columnDefs: [
            { targets: [5], orderable: false }
        ]
    });

}


function abrirModal(){

    const modalAsistencias = new bootstrap.Modal(document.getElementById("asistenciasModal"));

    modalAsistencias.show()
}


async function mostrarAsistencias(becarioId) {
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/admin/asistencia/" + becarioId,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (asistencias) {
            try {
                var lista = document.getElementById("asistenciasLista");
                lista.innerHTML = "";

                for (var i = 0; i < asistencias.length; i++) {
                    var asistencia = asistencias[i];
                    var item = document.createElement("li");
                    item.textContent = "Fecha: " + asistencia['fecha'] + ", Hora Inicio: " + asistencia['horaInicio'] + ", Hora Fin: " + asistencia['horaFin'];
                    lista.appendChild(item);
                }
            }catch (e) {
                return JSON.stringify("Error al cargar el modal")
            }

        },
        error: function (error) {
            console.log(error);
        }
    });
}