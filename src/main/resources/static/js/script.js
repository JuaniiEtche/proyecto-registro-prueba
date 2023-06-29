//const ip = "http://190.247.84.16:8080"
const ip ="http://localhost:8080"
const $btnLog = document.getElementById("logear");

const $btnRegistro = document.getElementById("registro");

const $btnRegistrar = document.getElementById("registrar");

const $btnVolverLogin = document.getElementById("volverLogin");

const $btnCerrarSesion = document.getElementById("cerrarSesion");

const $btnAsistenciasBecario = document.getElementById("dirigirAsistenciaBecario");

const $btnVolverInicio = document.getElementById("volverInicio");

const $btnVolverInicioA = document.getElementById("volverInicioA");

const $btnRegistrarAsistencia = document.getElementById("registrarAsistencia");

const $btnDirigirListadoB = document.getElementById("dirigirListadoB");

const $btnDirigirLineas = document.getElementById("dirigirLineas");

const $btnDirigirGabinete = document.getElementById("dirigirGabinete");

const $selectDepartamento = document.getElementById("departamento");

// Configuración de Toastr
toastr.options = {
    closeButton: true,
    progressBar: true,
    positionClass: 'toast-top-center',
    timeOut: 3000,
};


function volverLogin(){
    window.location.href="index.html";
}

function logear(){
    var usuario = document.getElementById("txtUser").value;
    var contraseña = document.getElementById("txtPass").value;

    if(usuario==""){
        toastr.warning("Campo usuario incompleto");
        return;
    }

    if(contraseña==""){
        toastr.warning("Campo contraseña incompleto");
        return;
    }

    let datos={
        "usuario":usuario,
        "clave":contraseña
    }

    $.ajax({
        type: "POST",
        url: ip+"/public/authenticate",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(datos),
        success: function (data) {
            localStorage.setItem("token",data["jwtToken"]);
            localStorage.setItem("usuario",usuario);

            const decodedToken = jwt_decode(data["jwtToken"]);

            const rol = decodedToken.rol.authority;

            if(rol=="ROLE_admin"){
                window.location.href="principalAdmin.html"
            }else{
                window.location.href="principalUser.html"
            }
        },
        failure: function (data) {
            alert(data.responseText);
        },
        error: function (data) {
            toastr.warning("Usuario/Contraseña incorrecto, o verifiqué si fue habilitado");
        }
    });
}

function dirRegistro(){
    window.location.href="registroUsuario.html"
}

function registrar(){

    const nombreUsuario = document.getElementById("nombreUsuario").value;
    const contraseña = document.getElementById("contrasenia").value;
    const nombrePersona = document.getElementById("nombrePersona").value;
    const apellido = document.getElementById("apellido").value;
    const dni = document.getElementById("dni").value;
    const email = document.getElementById("email").value;
    const telefono = document.getElementById("telefono").value;
    const legajo = document.getElementById("legajo").value;

    var camposVacios = false;

    if (nombreUsuario === "") {
        document.getElementById("nombreUsuario").style.borderColor = "red";
        camposVacios = true;
    }

    if (contraseña === "") {
        document.getElementById("contrasenia").style.borderColor = "red";
        camposVacios = true;
    }

    if (nombrePersona === "") {
        document.getElementById("nombrePersona").style.borderColor = "red";
        camposVacios = true;
    }

    if (apellido === "") {
        document.getElementById("apellido").style.borderColor = "red";
        camposVacios = true;
    }

    if (dni === "") {
        document.getElementById("dni").style.borderColor = "red";
        camposVacios = true;
    }

    if (email === "") {
        document.getElementById("email").style.borderColor = "red";
        camposVacios = true;
    }

    if (telefono === "") {
        document.getElementById("telefono").style.borderColor = "red";
        camposVacios = true;
    }

    if (legajo === "") {
        document.getElementById("legajo").style.borderColor = "red";
        camposVacios = true;
    }

    if (camposVacios) {
        toastr.warning("Por favor, completa todos los campos");
        return;
    }

    if(contraseña.length<8){
        toastr.warning("La contraseña debe tener al menos 8 caracteres");
    }

    let datos={
        nombreUsuario: nombreUsuario,
        contraseña: contraseña,
        nombrePersona:nombrePersona,
        apellido:apellido,
        dni:dni,
        email:email,
        numTelefono:telefono,
        legajo:legajo
    }

    $.ajax({
        type: "POST",
        url: ip+"/public/usuario",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(datos),
        success: function (data) {
            toastr.success("Usuario solicitado con exito");
            window.location.href="index.html"
        },
        failure: function (data) {
            alert(data.responseText);
        },
        error: function (data) {
            toastr.success("Usuario solicitado con exito");
            window.location.href="index.html"        }
    });

}


function obtenerYMostrarAsistencias() {
    var nombreUsuario = localStorage.getItem('usuario');
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+'/user/asistencia/' + nombreUsuario,
        type: 'GET',
        headers: {
            Authorization: 'Bearer ' + tokenJWT
        },
        success: function(response) {
            var tabla =
                '<table class="table">' +
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
}

function armarTablas(){
    var nombreUsuario = localStorage.getItem('usuario');
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/admin/user/" + nombreUsuario,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function(response) {
            let tablas = "";
            for (let i = 0; i < response.length; i++) {
                let nombreLinea = response[i]['nombreLinea'];

                // Crear el contenido de la tabla para cada línea
                let tabla = `<h5>${nombreLinea}</h5>
                <hr style="border-top: 1px solid grey">
                <div class="col-md-6">
                    <table class="table">
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
                        <button class="btn btn-primary">Editar</button>
                        <button class="btn btn-danger" onclick="eliminarUsuarioLinea(`+idPersona+`,'`+nombreLinea+`')">Eliminar</button>
                    </td>
                    </tr>`;
                }

                // Cerrar la tabla y agregarla al contenido total
                tabla += `</tbody>
                        </table>
                        </div>`;

                tablas += tabla;
            }

            // Insertar el contenido de las tablas en el elemento con id "tablaBecariosXLinea"
            document.getElementById("tablaBecariosXLinea").innerHTML = tablas;

        },
        error: function(xhr, status, error) {
            // Manejar el error aquí
            console.error(error);
        }
    });
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

function nombreUsuario(){
    var nombreUsuario = localStorage.getItem('usuario');

    const nombre = document.getElementById("bienvenida");

    nombre.innerHTML="Bienvenido/a "+nombreUsuario;
}

function cerrarSesion() {
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");

    window.location.href="./index.html"
}

function dirigirAsistenciasBecario() {
    window.location.href="./AsistenciaBecario.html"
}

function cargarLineas() {
    var cardDeckScroll = document.querySelector('.card-deck-scroll');
    var scrollBtnLeft = document.querySelector('.scroll-btn-left');
    var scrollBtnRight = document.querySelector('.scroll-btn-right');

    scrollBtnLeft.addEventListener('click', () => {
        const scrollAmount = 300;
        cardDeckScroll.scrollBy({
            left: -scrollAmount,
            behavior: 'smooth'
        });
    });

    scrollBtnRight.addEventListener('click', () => {
        const scrollAmount = 300;
        cardDeckScroll.scrollBy({
            left: scrollAmount,
            behavior: 'smooth'
        });
    });

    cardDeckScroll.addEventListener('scroll', () => {
        const scrollAmount = 300;
        scrollBtnLeft.style.opacity = cardDeckScroll.scrollLeft === 0 ? 0 : 1;
        scrollBtnRight.style.opacity = cardDeckScroll.scrollLeft + cardDeckScroll.clientWidth >= cardDeckScroll.scrollWidth ? 0 : 1;
    });

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/user/lineaInvestigacion",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {
            var cardContainer = document.getElementById('cardContainer');

            data.forEach(function (item) {
                var card = document.createElement('div');
                card.classList.add('card');

                var cardBody = document.createElement('div');
                cardBody.classList.add('card-body');

                var cardTitle = document.createElement('h5');
                cardTitle.classList.add('card-title');
                cardTitle.textContent = item.nombre;

                var cardDescription = document.createElement('p');
                cardDescription.classList.add('card-text');
                cardDescription.textContent = item.descripcion;

                cardBody.appendChild(cardTitle);
                cardBody.appendChild(cardDescription);
                card.appendChild(cardBody);
                cardContainer.appendChild(card);
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function volverInicio() {
    window.location.href="./principalUser.html"
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

function dirigirListadoB() {
    window.location.href="./listadoBecarios.html"
}

function volverInicioA() {
    window.location.href="./principalAdmin.html"
}

function cargarTablaBecarios() {
    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip + "/admin/personas",
        type: "GET",
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

}

function abrirModalAñadirReserva() {
    const modalAsistencias = new bootstrap.Modal(document.getElementById("reservaModal"));

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


function abrirModal(){

    const modalAsistencias = new bootstrap.Modal(document.getElementById("asistenciasModal"));

    modalAsistencias.show()
}

function dirigirLineas() {
    window.location.href="./lineasInvestigacion.html";
}

function dirigirGabinete() {
    window.location.href="./gabinete.html";
}

function armarFooter() {
    const footer = document.getElementById("footer");

    footer.innerHTML="\n" +
        "\n" +
        " <br>   " +
        "<footer style=\"background-color: #f2f2f2; padding: 10px;\">\n" +
        "        <div>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col-lg-6\">\n" +
        "                    <img src=\"./img/utn-frlp-logo.png\" alt=\"Logo\" style=\"height: 30px;\">\n" +
        "                </div>\n" +
        "                <div class=\"col-lg-6 text-right\">\n" +
        "                    <a href=\"https://www.instagram.com/lablinsi/\" target=\"_blank\"><img src=\"./img/instagram-logo.png\" alt=\"Instagram\" style=\"height: 30px; margin-right: 10px;\"></a>\n" +
        "                    <a href=\"https://www.facebook.com/utnblockchainlab\" target=\"_blank\"><img src=\"./img/facebook-logo.png\" alt=\"Facebook\" style=\"height: 30px;\"></a>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <hr>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col-lg-12 text-center\">\n" +
        "                    <p>LAB-LINSI FRLP UTN. Copyrigth 2023</p>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </footer>"
}

function armarCabeceraAdmin() {
    const cabecera = document.getElementById("cabecera-admin");
    cabecera.innerHTML="    <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
        "        <a class=\"navbar-brand\">Laboratorio Linsi</a>\n" +
        "        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
        "            <span class=\"navbar-toggler-icon\"></span>\n" +
        "        </button>\n" +
        "        <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
        "            <ul class=\"navbar-nav\">\n" +
        "                <li class=\"nav-item active\">\n" +
        "                    <a class=\"nav-link\" href=\"#\" id=\"volverInicioA\" onclick='volverInicioA()'>Inicio</a>\n" +
        "                </li>\n" +
        "                <li class=\"nav-item\">\n" +
        "                    <a class=\"nav-link\" href=\"#\" id=\"dirigirListadoB\" onclick='dirigirListadoB()'>Becarios</a>\n" +
        "                </li>\n" +
        "                <li class=\"nav-item\">\n" +
        "                    <a class=\"nav-link\" href=\"#\" id=\"dirigirLineas\" onclick='dirigirLineas()'>Lineas de investigacion</a>\n" +
        "                </li>\n" +
        "                <li class=\"nav-item\">\n" +
        "                    <a class=\"nav-link\" href=\"#\" id=\"dirigirGabinete\" onclick='dirigirGabinete()'>Reservas de gabinetes</a>\n" +
        "                </li>\n" +
        "            </ul>\n" +
        "        </div>\n" +
        "        <a class=\"btn btn-outline-danger my-2 my-sm-0\" id=\"cerrarSesion\" href=\"#\" onclick='cerrarSesion()'>Cerrar sesion</a>\n" +
        "    </nav>"
}

function armarCabeceraUser() {
    const cabecera = document.getElementById("cabecera-user");

    cabecera.innerHTML="<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
        "    <a class=\"navbar-brand\">Laboratorio Linsi</a>\n" +
        "    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\"\n" +
        "            aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
        "        <span class=\"navbar-toggler-icon\"></span>\n" +
        "    </button>\n" +
        "    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
        "        <ul class=\"navbar-nav\">\n" +
        "            <li class=\"nav-item active\">\n" +
        "                <a class=\"nav-link\" href=\"#\" onclick='volverInicio()'>Inicio</a>\n" +
        "            </li>\n" +
        "            <li class=\"nav-item\">\n" +
        "                <a class=\"nav-link\" href=\"#\" id=\"dirigirAsistenciaBecario\" onclick='dirigirAsistenciasBecario()'>Asistencia</a>\n" +
        "            </li>\n" +
        "        </ul>\n" +
        "    </div>\n" +
        "    <a class=\"btn btn-outline-danger my-2 my-sm-0\" id=\"cerrarSesion\" href=\"#\" onclick='cerrarSesion()'>Cerrar sesion</a>\n" +
        "</nav>\n"
}

function cargarReservas() {

    var tokenJWT = localStorage.getItem('token');

    var eventos = [];

    $.ajax({
        url: "http://localhost:8080/admin/reservas-confirmadas",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {

            for (let i = 0; i < data.length; i++) {
                var fechaInicio = data[i]['fecha']+' '+data[i]['horaInicio'];

                var partes = fechaInicio.split(" "); // Separar la fecha y la hora
                var fechaPartes = partes[0].split("-"); // Separar las partes de la fecha
                var horaPartes = partes[1].split(":"); // Separar las partes de la hora

                // Crear el objeto de fecha en JavaScript
                var fechaInicio = new Date(
                    parseInt(fechaPartes[0]),        // Año
                    parseInt(fechaPartes[1]) - 1,    // Mes (se resta 1 porque en JavaScript los meses comienzan desde 0)
                    parseInt(fechaPartes[2]),        // Día
                    parseInt(horaPartes[0]),         // Hora
                    parseInt(horaPartes[1]),         // Minuto
                    parseInt(horaPartes[2])          // Segundo
                );


                var fechaFin = data[i]['fecha']+' '+data[i]['horaFin'];

                var partes = fechaFin.split(" "); // Separar la fecha y la hora
                var fechaPartes = partes[0].split("-"); // Separar las partes de la fecha
                var horaPartes = partes[1].split(":"); // Separar las partes de la hora

                // Crear el objeto de fecha en JavaScript
                var fechaFin = new Date(
                    parseInt(fechaPartes[0]),        // Año
                    parseInt(fechaPartes[1]) - 1,    // Mes (se resta 1 porque en JavaScript los meses comienzan desde 0)
                    parseInt(fechaPartes[2]),        // Día
                    parseInt(horaPartes[0]),         // Hora
                    parseInt(horaPartes[1]),         // Minuto
                    parseInt(horaPartes[2])          // Segundo
                );


                let materia = data[i]['nombreMateria'];
                let horaInicio = fechaInicio;
                let horaFin = fechaFin;
                let descripcion = data[i]['nombreDepartamento'];
                let profesor = data[i]['nombreDocente'];
                let profesorapellido = data[i]['apellidoDocente'];
                let email = data[i]['email'];
                let telefono = data[i]['telefono'];

                var propiedad = {
                    title: 'Materia: ' + materia,
                    start: horaInicio,
                    end: horaFin,
                    description: descripcion,
                    professor: profesor+" "+profesorapellido,
                    email:email,
                    telefono:telefono
                };
                eventos.push(propiedad);
            }
            $('#calendar').fullCalendar({
                defaultView: 'month',
                displayEventTime: false, // Ocultar la hora de inicio del evento
                events: eventos,
                eventClick: function(calEvent, jsEvent, view) {
                    mostrarDetallesEvento(calEvent);
                },
                    /*dayClick: function(date, jsEvent, view) {
                    // Obtener los detalles del día seleccionado
                    var dayDetails = getDayDetails(date);

                    // Mostrar los detalles en el modal
                    $('#dayModalTitle').text(dayDetails.title);
                    $('#dayModalBody').html(dayDetails.details);
                    $('#dayModal').modal('show');
                     },
                    */
            });
        },
        error: function (error) {
            console.log(error);
        }
    });

}

function mostrarDetallesEvento(eventDetails) {
    var fechaInicio = new Date(eventDetails.start)
    var fechaFin = new Date(eventDetails.end)
    var horas = fechaInicio.getHours();
    var minutos = fechaInicio.getMinutes();

    // Formatear la hora y los minutos en formato HH:mm
    var horaMinutos = ("0" + horas).slice(-2) + ":" + ("0" + minutos).slice(-2);

    var horas2 = fechaFin.getHours();
    var minutos2 = fechaFin.getMinutes();

    // Formatear la hora y los minutos en formato HH:mm
    var horaMinutos2 = ("0" + horas2).slice(-2) + ":" + ("0" + minutos2).slice(-2);

    var fechaHora = new Date(eventDetails.start);

    var opcionesFecha = { day: 'numeric', month: 'long', year: 'numeric' };
    var fechaFormateada = fechaHora.toLocaleDateString('es-ES', opcionesFecha);


    $('#eventTitle').text(eventDetails.title);
    $('#eventDate').text('Fecha: ' +fechaFormateada);
    $('#eventStartAndEnd').text('Hora: desde ' + horaMinutos + ' hasta las ' +
        horaMinutos2);
    $('#eventDescription').text("Departamento: "+eventDetails.description);
    $('#eventProfessor').text("Profesor: "+eventDetails.professor);
    $('#eventNumTelefono').text('Telefono: '+ eventDetails.telefono);
    $('#eventEmail').text('Email: '+ eventDetails.email);

    $('#eventModal').modal('show');
}

function getDayDetails(date) {
    // Aquí puedes implementar la lógica para obtener los detalles del día
    // Puedes consultar una base de datos, un archivo JSON, etc.
    // Por ahora, retornaremos algunos datos de ejemplo
    return {
        title: 'Detalles del día',
        details: 'Aquí puedes agregar los detalles específicos del día seleccionado.'
    };
}


function inicializar() {

    // Inicializar el selector de fecha
    var fechaPicker = $('#fecha').pickadate({
        format: 'dd-mm-yyyy',
        min: true,
        monthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthsShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
        weekdaysFull: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
        weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'],
        today: false,
        clear: false,
        close: false,
        closeOnSelect: true,
        onSet: function() {
            // Aquí puedes realizar una llamada al servidor para obtener los horarios disponibles según la fecha seleccionada
            // y actualizar el campo de selección "horario" utilizando algo como:
            // $('#horario').html('<option value="horario1">Horario 1</option><option value="horario2">Horario 2</option>');
        }
    })
    // Inicializar el selector de hora
    var horarioPicker = $('#horario').pickatime({
        format: 'HH:i', // Formato de hora
        interval: 30, // Intervalo de minutos para seleccionar
        clear: false, // Deshabilitar el botón "Limpiar"
        close: false, // Deshabilitar el botón "Cerrar"
    });

    // Inicializar el selector de hora
    var horarioPicker2 = $('#horarioFin').pickatime({
        format: 'HH:i', // Formato de hora
        interval: 30, // Intervalo de minutos para seleccionar
        clear: false, // Deshabilitar el botón "Limpiar"
        close: false, // Deshabilitar el botón "Cerrar"
    });
}

function cargarTablaLinea(){
    var tokenJWT = localStorage.getItem('token');
    $.ajax({
            url: "http://localhost:8080/admin/lineaInvestigacion",
            type: "GET",
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
}

// Crear un objeto para almacenar las materias por departamento
var materiasPorDepartamento = {}

async function listarDepartamentos() {
    cargarGabinetes()

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip + "/admin/departamento",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (departamentos) {
            var selectDepartamento = $('#departamento');
            selectDepartamento.empty();

            for (let i = 0; i < departamentos.length; i++) {
                var departamento = departamentos[i];

                // Agregar el departamento al diccionario
                materiasPorDepartamento[departamento.nombre] = departamento.nombreMaterias;

                var newOption = $('<option>').text(departamento.nombre).val(departamento.nombre);
                selectDepartamento.append(newOption);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}


function cambiarMaterias() {
    $('#departamento').change(function () {
        var seleccionado = $(this).val();
        var selectMateria = $('#materia');

        selectMateria.off('change'); // Desactivar el evento change del select de materias

        selectMateria.empty(); // Vaciar el select2

        // Obtener las materias del departamento seleccionado
        var materiasDepartamento = materiasPorDepartamento[seleccionado];

        if (materiasDepartamento && materiasDepartamento.length > 0) {
            // Agregar las materias al select de materias
            for (var i = 0; i < materiasDepartamento.length; i++) {
                var materia = materiasDepartamento[i];
                var newOption = $('<option>').text(materia).val(materia);
                selectMateria.append(newOption);
            }
        } else {
            // Mostrar un mensaje si no hay materias para el departamento seleccionado
            var mensaje = 'No hay materias disponibles para este departamento.';
            var newOption = $('<option disabled selected>').text(mensaje);
            selectMateria.append(newOption);
        }

        selectMateria.on('change', cambiarDocentes); // Asignar nuevamente el evento change del select de materias

        selectMateria.trigger('change'); // Actualizar el select2 para reflejar los cambios
    });
}

function cargarGabinetes() {
    var checkboxesContainer = $('#checkboxesGabinetes');
    checkboxesContainer.empty(); // Vaciar el contenedor de checkboxes

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip + "/admin/gabinete",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (gabinetes) {
            var checkboxesContainer = $('#checkboxesGabinetes');
            checkboxesContainer.empty(); // Vaciar los checkboxes existentes

            for (var i = 0; i < gabinetes.length; i++) {
                var gabinete = gabinetes[i];

                // Crear el elemento checkbox
                var checkbox = $('<input>').attr({
                    type: 'checkbox',
                    id: 'gabinete' + i,
                    value: gabinete.nombre,
                    'data-cantidadEquipos': gabinete.cantidadEquipos // Agregar atributo personalizado para la cantidad de equipos
                });

                // Crear la etiqueta para el checkbox
                var label = $('<label>').attr('for', 'gabinete' + i).text(gabinete.nombre + ' (' + gabinete.cantidadEquipos + ' equipos)');

                // Crear el div contenedor del checkbox y su etiqueta
                var checkboxDiv = $('<div>').addClass('checkbox-container');

                // Agregar el checkbox y la etiqueta al div contenedor
                checkboxDiv.append(checkbox, label);

                // Agregar el div contenedor al contenedor principal de los checkboxes
                checkboxesContainer.append(checkboxDiv);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
    // Agregar un controlador de eventos al cambio de estado del checkbox
    checkboxesContainer.on('change', 'input[type="checkbox"]', function () {
        var contadorEquipos = 0;

        // Obtener los checkboxes seleccionados y sumar la cantidad de equipos correspondiente
        checkboxesContainer.find('input[type="checkbox"]:checked').each(function () {
            contadorEquipos += parseInt($(this).attr('data-cantidadEquipos'), 10);
        });

        $('#contadorEquipos').text("Cantidad total de equipos: "+contadorEquipos);
    });
}



function cambiarDocentes() {
    var tokenJWT = localStorage.getItem('token');

    var seleccionado = $('#materia').val();
    var selectProfesor = $('#profesor');

    selectProfesor.empty(); // Vaciar el select2

    $.ajax({
        url: ip + "/admin/docente/" + seleccionado,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (docentes) {
            if (docentes && docentes.length > 0) {
                // Agregar los docentes a partir de la materia
                for (var i = 0; i < docentes.length; i++) {
                    var docente = docentes[i];
                    var newOption2 = $('<option>').text(docente['apellido'] +' '+ docente['nombre']).val(docente['apellido']);
                    selectProfesor.append(newOption2);
                }
            } else {
                var mensaje = 'No hay profesores disponibles para esta materia.';
                var newOption2 = $('<option disabled selected>').text(mensaje);
                selectProfesor.append(newOption2);
            }

            selectProfesor.trigger('change');
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function altaReserva() {
    var fecha=document.getElementById("fecha").value
    var horaInicio=document.getElementById("horario").value
    var horaFin=document.getElementById("horarioFin").value
    var listaNombreGabinetes=[]
    var nombreMateria=document.getElementById("materia").value
    var nombreDepartamento=document.getElementById("departamento").value
    var nombreProfesor=document.getElementById("profesor").value

    if(fecha===""){
        toastr.warning("No se ha introducido una fecha valida.")
        return;
    }

    if(horaInicio===""){
        toastr.warning("No se ha introducido una hora de inicio valida.")
        return;
    }
    if(horaFin===""){
        toastr.warning("No se ha introducido una hora de fin valida.")
        return;
    }


    if(nombreDepartamento===""){
        toastr.warning("No se ha seleccionado ningún departamento.")
        return;
    }
    if(nombreMateria==="" || nombreMateria==="No hay materias disponibles para este departamento."){
        toastr.warning("No se ha seleccionado ninguna materia.")
        return;
    }
    if(nombreProfesor==="" || nombreProfesor==="No hay profesores disponibles para esta materia."){
        toastr.warning("No se ha seleccionado ningún profesor.")
        return;
    }


    var partesFecha = fecha.split("-"); // Divide la cadena en partes separadas por el guion ("-")
    var fecha = new Date(partesFecha[2], partesFecha[1] - 1, partesFecha[0]); // Crea un objeto Date con las partes de la fecha (año, mes, día)


    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            listaNombreGabinetes.push(checkboxes[i].value);
        }
    }

    if(listaNombreGabinetes.length===0) {
        toastr.warning("No has seleccionado ningún gabinete a reservas.")
        return;
    }

    reserva={
        fecha:fecha,
        horaInicio:horaInicio,
        horaFin:horaFin,
        nombreMateria:nombreMateria,
        apellidoDocente:nombreProfesor,
        nombreGabinete:listaNombreGabinetes
    }

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+'/admin/reserva',
        type: 'POST',
        headers: {
            Authorization: 'Bearer ' + tokenJWT,
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(reserva),
        success: function(response) {
            toastr.success("Reserva realizada con exito")
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}


$btnLog?.addEventListener("click",logear);

$btnRegistro?.addEventListener("click",dirRegistro);

$btnVolverLogin?.addEventListener("click",volverLogin);

$btnRegistrar?.addEventListener("click",registrar);

$btnCerrarSesion?.addEventListener("click",cerrarSesion);

$btnAsistenciasBecario?.addEventListener("click",dirigirAsistenciasBecario);

$btnVolverInicio?.addEventListener("click",volverInicio);

$btnVolverInicioA?.addEventListener("click",volverInicioA);

$btnRegistrarAsistencia?.addEventListener("click",registrarAsistencia);

$btnDirigirListadoB?.addEventListener("click",dirigirListadoB);

$btnDirigirLineas?.addEventListener("click",dirigirLineas);

$btnDirigirGabinete?.addEventListener("click",dirigirGabinete)












