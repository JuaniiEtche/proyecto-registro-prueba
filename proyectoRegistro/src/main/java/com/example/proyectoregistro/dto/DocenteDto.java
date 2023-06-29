package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Materia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDto {

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

}
