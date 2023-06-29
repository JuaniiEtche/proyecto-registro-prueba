package com.example.proyectoregistro.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LineaInvestigacionDto {

    private String nombre;

    private String descripcion;
}
