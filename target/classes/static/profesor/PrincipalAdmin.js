

function armarTablas() {
    var nombreUsuario = localStorage.getItem('usuario');
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip + "/admin/user/" + nombreUsuario,
        type: "GET",
        async: false,
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function(response) {
            let tablas = "";
            for (let i = 0; i < response.length; i++) {
                let nombreLinea = response[i]['nombreLinea'];

                // Crear el contenido de la tabla para cada línea
                let tablaClass = "tablaBecario"; // Clase para cada tabla
                let tabla = `<h5>${nombreLinea}</h5>
          <hr style="border-top: 1px solid grey">
          <div class="col-md-6">
            <table class="table ${tablaClass}">
              <thead class="table-ligth">
                <th scope="col">BECARIO</th>
                <th scope="col">LEGAJO</th>
                <th scope="col">ACCIONES</th>
              </thead>
              <tbody>`;

                for (let j = 0; j < response[i]['personaDtos'].length; j++) {
                    let idPersona = response[i]['personaDtos'][j]['idPersona'];
                    let nombre = response[i]['personaDtos'][j]['nombre'];
                    let apellido = response[i]['personaDtos'][j]['apellido'];
                    let legajo = response[i]['personaDtos'][j]['legajo'];

                    // Agregar una fila a la tabla para cada persona
                    tabla += `<tr>
            <td>${nombre} ${apellido}</td>
            <td>${legajo}</td>
            <td>
              <button class="btn btn-primary" onclick="verDetallesBecario(${idPersona})">Ver Detalles</button>
              <button class="btn btn-danger" onclick="eliminarUsuarioLinea(${idPersona},'${nombreLinea}')">Eliminar</button>
            </td>
          </tr>`;
                }

                // Cerrar la tabla y agregarla al contenido total
                tabla += `</tbody>
          </table>
            <br>
        </div>`;

                tablas += tabla;
            }

            // Insertar el contenido de las tablas en el elemento con id "tablaBecariosXLinea"
            document.getElementById("tablaBecariosXLinea").innerHTML = tablas;

            // Inicializar DataTables para todas las tablas con la clase específica
            $('.tablaBecario').DataTable({
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json'
                },
                columns: [
                    null,
                    { searchable: false },
                ],
                columnDefs: [
                    { targets: [2], orderable: false }
                ],
                info: false,
                paging: false,
                searching: false
            });
        },
        error: function(xhr, status, error) {
            // Manejar el error aquí
            console.error(error);
        }
    });
}



function nombreUsuario(){
    var nombreUsuario = localStorage.getItem('usuario');

    const nombre = document.getElementById("bienvenida");

    nombre.innerHTML="Bienvenido/a "+nombreUsuario;
}


function eliminarUsuarioLinea(idPersona,nombreLinea) {

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/admin/usuarioxlinea/" + idPersona + "/" + nombreLinea,
        type: "DELETE",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (response) {
            location.reload();

        },
        error: function (xhr, status, error) {
            // Manejar el error aquí
            console.error(error);
        }
    });
}