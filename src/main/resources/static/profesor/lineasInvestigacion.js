
function cargarTablaLinea(){
    var tokenJWT = localStorage.getItem('token');
    $.ajax({
        url: ip+"/admin/lineaInvestigacion",
        type: "GET",
        async:false,
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {
            //var lista = document.getElementById("idLinea");
            for (let i = 0; i < data.length; i++) {
                // Obtener la referencia a la tabla
                var tabla2 = document.getElementById("tablaLineas");
                // Obtener la referencia al cuerpo de la tabla
                var cuerpoTabla2 = tabla2.getElementsByTagName("tbody")[0];

                // Crear una nueva fila
                var nuevaFila = cuerpoTabla2.insertRow();

                // Crear celdas y agregar contenido
                var celdaNombre = nuevaFila.insertCell();
                celdaNombre.textContent = data[i]['nombre']

                var celdaDescripcion = nuevaFila.insertCell();
                celdaDescripcion.textContent = data[i]['descripcion'];

                var celdaBoton2 = nuevaFila.insertCell();

                var boton = document.createElement("button");
                boton.textContent = "Ver proyectos";
                boton.classList.add("btn", "btn-secondary")

                // boton.addEventListener("click", function() {
                //     mostrarAsistencias(data[i]['idPersona']).then(abrirModal).catch(console.error); // Pasar el ID del becario al mostrar asistencias
                // })

                // Agregar el botón a la celda correspondiente
                celdaBoton2.appendChild(boton);

                // Agregar la fila al cuerpo de la tabla
                //cuerpoTabla2.appendChild(nuevaFila);

            }
        },
        error: function (error) {
            console.log(error);
        }
    });
    $('#tablaLineas').DataTable({
        language: {
            url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json'
        },
        columns: [
            null, // Columna 1 - Se permitirá la búsqueda
            { searchable: false }, // Columna 2 - No se permitirá la búsqueda
            { searchable: false } // Columna 3 - No se permitirá la búsqueda
        ],
        columnDefs: [
            { targets: [1,2], orderable: false }
        ]
    });
}