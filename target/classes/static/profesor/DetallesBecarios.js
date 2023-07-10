function cargarDatosPersonales(){
    var tokenJWT = localStorage.getItem('token');

    // Obtener la URL actual
    var url = window.location.href;

    // Crear un objeto URL con la URL actual
    var urlObj = new URL(url);

    // Obtener el valor de un parámetro específico
    var parametro = urlObj.searchParams.get('idPersona');

    $.ajax({
        url: ip+'/admin/personas/'+parametro,
        type: 'GET',
        headers: {
            Authorization: 'Bearer ' + tokenJWT,
            'Content-Type': 'application/json'
        },
        success: function(response) {

            $('#nombreBecario').html("Información del becario: "+response['nombre']+ " " + response['apellido']); // Establecer el valor del campo de entrada


            $('#nombre_apellido').val(response['nombre']+ " " + response['apellido']); // Establecer el valor del campo de entrada

            $('#dni').val(response['dni']); // Establecer el valor del campo de entrada

            $('#telefono').val(response['numTelefono']); // Establecer el valor del campo de entrada

            $('#email').val(response['email']); // Establecer el valor del campo de entrada

            $('#legajo').val(response['legajo']); // Establecer el valor del campo de entrada

        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}




function cargarDatosAcademicos(){
    var tokenJWT = localStorage.getItem('token');

    // Obtener la URL actual
    var url = window.location.href;

    // Crear un objeto URL con la URL actual
    var urlObj = new URL(url);

    // Obtener el valor de un parámetro específico
    var parametro = urlObj.searchParams.get('idPersona');

    $.ajax({
        url: ip+'/admin/datosacademicos/'+parametro,
        type: 'GET',
        headers: {
            Authorization: 'Bearer ' + tokenJWT,
            'Content-Type': 'application/json'
        },
        success: function(response) {

            $('#carrera').val(response['carrera']); // Establecer el valor del campo de entrada

            $('#anio_ingreso').val(response['añoIngreso']); // Establecer el valor del campo de entrada

            $('#anio_cursada').val(response['añoCursada']); // Establecer el valor del campo de entrada

            $('#materias_cursadas').val(response['cantMateriasCursadas']); // Establecer el valor del campo de entrada

            $('#materias_aprobadas').val(response['cantMateriasAprobadas']); // Establecer el valor del campo de entrada

            $('#materias_que_cursa').val(response['cantMateriasQueCursa']); // Establecer el valor del campo de entrada


        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}



function cargarProyectosAsignados(){
    var tokenJWT = localStorage.getItem('token');

    // Obtener la URL actual
    var url = window.location.href;

    // Crear un objeto URL con la URL actual
    var urlObj = new URL(url);

    // Obtener el valor de un parámetro específico
    var parametro = urlObj.searchParams.get('idPersona');

    $.ajax({
        url: ip+'/admin/proyecto/'+parametro,
        type: 'GET',
        headers: {
            Authorization: 'Bearer ' + tokenJWT,
            'Content-Type': 'application/json'
        },
        success: function(data) {
            for (let i = 0; i < data.length; i++) {
                // Obtener la referencia a la tabla
                var tablaProyectos = document.getElementById("tablaProyectos");
                // Obtener la referencia al cuerpo de la tabla
                var cuerpoTablaProyecto = tablaProyectos.getElementsByTagName("tbody")[0];

                // Crear una nueva fila
                var nuevaFila = cuerpoTablaProyecto.insertRow();

                // Crear celdas y agregar contenido
                var celdaNombre = nuevaFila.insertCell();
                celdaNombre.textContent = data[i]['nombre']

                var celdaDescripcion = nuevaFila.insertCell();
                celdaDescripcion.textContent = data[i]['descripcion'];

                var celdaFechaInicio = nuevaFila.insertCell();
                celdaFechaInicio.textContent = data[i]['fechaInicio'];

                var celdaFechaFin = nuevaFila.insertCell();
                celdaFechaFin.textContent = data[i]['fechaFin'];

                var celdaFechaFin = nuevaFila.insertCell();
                celdaFechaFin.textContent = data[i]['lineaInvestigacion'];

            }
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}