
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
            window.location.href="./index.html"
        },
        failure: function (data) {
            alert(data.responseText);
        },
        error: function (data) {
            toastr.success("Usuario solicitado con exito");
            window.location.href="./index.html"        }
    });

}