package com.example.proyectoregistro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private long idUsuario;

    private String nombre;

    private String rol;

}
