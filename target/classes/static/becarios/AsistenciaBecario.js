
function obtenerYMostrarAsistencias() {
    var nombreUsuario = localStorage.getItem('usuario');
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+'/user/asistencia/' + nombreUsuario,
        type: 'GET',
        async:false,
        headers: {
            Authorization: 'Bearer ' + tokenJWT
        },
        success: function(response) {
            var tabla =
                '<table class="table tablaAsistencias">' +
                '<thead>' +
                '<tr>' +
                '<th>Fecha</th>' +
                '<th>Hora de inicio</th>' +
                '<th>Hora de fin</th>' +
                '</tr>' +
                '</thead>' +
                '<tbody>';

            for (var i = 0; i < response.length; i++) {
                var asistencia = response[i];
                var fecha = asistencia.fecha;
                var horaInicio = asistencia.horaInicio;
                var horaFin = asistencia.horaFin;

                tabla +=
                    '<tr>' +
                    '<td>' + fecha + '</td>' +
                    '<td>' + horaInicio + '</td>' +
                    '<td>' + horaFin + '</td>' +
                    '</tr>';
            }

            tabla += '</tbody>' +
                '</table>';

            document.getElementById('tablaAsistencias').innerHTML = tabla;
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
    $('.tablaAsistencias').DataTable({
        language: {
            url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json'
        },
        searching: false,

        columnDefs: [
            { targets: [1,2], orderable: false }
        ]
    });
}


function marcarFin(registro) {

    var fechaActual = new Date();
    var hora = fechaActual.getHours().toString().padStart(2, '0');
    var minutos = fechaActual.getMinutes().toString().padStart(2, '0');
    var segundos = fechaActual.getSeconds().toString().padStart(2, '0');
    var horaFormateada = `${hora}:${minutos}:${segundos}`;

    registro.horaFin=horaFormateada;

    var nombreUsuario = localStorage.getItem('usuario');
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+'/user/asistencia/' + nombreUsuario,
        type: 'POST',
        headers: {
            Authorization: 'Bearer ' + tokenJWT,
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(registro),
        success: function(response) {
            var tabla = document.getElementById("tablaAsistencias");
            var cuerpoTabla = tabla.getElementsByTagName("tbody")[0];
            var ultimaFilaIndex = cuerpoTabla.rows.length - 1;
            var ultimaFila = cuerpoTabla.rows[ultimaFilaIndex];

            var celdaHoraFin = ultimaFila.cells[2];
            celdaHoraFin.textContent=registro.horaFin;

            document.getElementById("registrarAsistencia").style.display='block'

        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });

}

function registrarAsistencia() {

    document.getElementById("registrarAsistencia").style.display='none'


    var fechaActual = new Date();
    var año = fechaActual.getFullYear();
    var mes = (fechaActual.getMonth() + 1).toString().padStart(2, '0');
    var dia = fechaActual.getDate().toString().padStart(2, '0');
    var fechaFormateada = `${año}-${mes}-${dia}`;

    var hora = fechaActual.getHours().toString().padStart(2, '0');
    var minutos = fechaActual.getMinutes().toString().padStart(2, '0');
    var segundos = fechaActual.getSeconds().toString().padStart(2, '0');
    var horaFormateada = `${hora}:${minutos}:${segundos}`;

    var registro = {
        fecha: fechaFormateada,
        horaInicio: horaFormateada,
    };

    // Obtener la referencia a la tabla
    var tabla = document.getElementById("tablaAsistencias");

    // Obtener la referencia al cuerpo de la tabla
    var cuerpoTabla = tabla.getElementsByTagName("tbody")[0];

    // Crear una nueva fila
    var nuevaFila = cuerpoTabla.insertRow();

    // Crear celdas y agregar contenido
    var celdaNombre = nuevaFila.insertCell();
    celdaNombre.textContent = fechaFormateada;

    var celdaEdad = nuevaFila.insertCell();
    celdaEdad.textContent = horaFormateada;

    var celdaBoton = nuevaFila.insertCell();

    var boton = document.createElement("button");
    boton.textContent = "Marcar Hora de Fin";
    boton.classList.add("btn", "btn-secondary")

    boton.addEventListener("click", function() {
        marcarFin(registro);
    })

    // Agregar el botón a la celda correspondiente
    celdaBoton.appendChild(boton);

    // Agregar la fila al cuerpo de la tabla
    cuerpoTabla.appendChild(nuevaFila);

}