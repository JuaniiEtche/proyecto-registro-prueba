package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.PersonaXGabinete;
import com.example.proyectoregistro.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

    private long idPersona;

    private String nombre;

    private String apellido;

    private int dni;

    private String email;

    private long numTelefono;

    private int legajo;

}
