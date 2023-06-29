package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    //DATOS PERSONA
    private String nombrePersona;

    private String apellido;

    private int dni;

    private String email;

    private long numTelefono;

    //DATOS USUARIO
    private String nombreUsuario;

    private String contraseña;

}
