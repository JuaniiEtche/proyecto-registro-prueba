const ip ="https://prueba-qeau.onrender.com"


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
    window.location.href="./index.html";
}

function dirRegistro(){
    window.location.href="./registroUsuario.html"
}

function verDetallesBecario(idPersona) {
    window.location.href="./DetallesBecarios.html?idPersona="+idPersona;
}

function cerrarSesion() {
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");

    window.location.href="../index.html"
}

function dirigirAsistenciasBecario() {
    window.location.href="./AsistenciaBecario.html"
}

function volverInicio() {
    window.location.href="./principalUser.html"
}

function dirigirListadoB() {
    window.location.href="./listadoBecarios.html"
}

function volverInicioA() {
    window.location.href="./principalAdmin.html"
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
        "                    <img src=\"/src/main/resources/static/img/logo.jpg\" alt=\"Logo\" style=\"height: 30px;\">\n" +
        "                </div>\n" +
        "                <div class=\"col-lg-6 text-right\">\n" +
        "                    <a href=\"https://www.instagram.com/lablinsi/\" target=\"_blank\"><img src=\"/src/main/resources/static/img/instagram-logo.png\" alt=\"Instagram\" style=\"height: 30px; margin-right: 10px;\"></a>\n" +
        "                    <a href=\"https://www.facebook.com/utnblockchainlab\" target=\"_blank\"><img src=\"/src/main/resources/static/img/facebook-logo.png\" alt=\"Facebook\" style=\"height: 30px;\"></a>\n" +
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

function cargarTablaPendiente() {

    var tokenJWT = localStorage.getItem('token');

    $.ajax({
        url: ip+"/admin/reservas-pendientes",
        type: "GET",
        headers: {
            "Authorization": "Bearer " + tokenJWT
        },
        success: function (data) {



            for (let i = 0; i < data.length; i++) {
                // Obtener la referencia a la tabla
                var tabla3 = document.getElementById("reservasPendientes");

                // Obtener la referencia al cuerpo de la tabla
                var cuerpoTabla3 = tabla3.getElementsByTagName("tbody")[0];

                // Crear una nueva fila
                var nuevaFila3 = cuerpoTabla3.insertRow();

                // Crear celdas y agregar contenido
                var celdaNombre3 = nuevaFila3.insertCell();
                celdaNombre3.textContent = data[i].idReserva;

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i].apellidoDocente;

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i].nombreMateria;

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i].fecha;

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i].horaInicio.substring(0, 5);

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i]['horaFin'].substring(0, 5);

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i].telefono;

                var celdaEdad3 = nuevaFila3.insertCell();
                celdaEdad3.textContent = data[i]['email'];


                var celdaBoton3 = nuevaFila3.insertCell();
                celdaBoton3.style.display = "flex";

                var botonAceptar = document.createElement("button");
                botonAceptar.textContent = "Aceptar";
                botonAceptar.classList.add("btn", "btn-success");
                celdaBoton3.appendChild(botonAceptar);

                var botonRechazar = document.createElement("button");
                botonRechazar.textContent = "Rechazar";
                botonRechazar.classList.add("btn", "btn-danger");
                celdaBoton3.appendChild(botonRechazar);



                // Agregar la fila al cuerpo de la tabla
                cuerpoTabla3.appendChild(nuevaFila3);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });

}



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












