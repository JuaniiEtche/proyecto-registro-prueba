
const $btnLog = document.getElementById("logear");

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
                window.location.href= "./profesor/principalAdmin.html"
            }else{
                window.location.href="./becarios/principalUser.html"
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


$btnLog?.addEventListener("click",logear);
