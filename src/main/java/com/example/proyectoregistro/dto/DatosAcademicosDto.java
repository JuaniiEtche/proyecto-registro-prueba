package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosAcademicosDto {

    private long idDatosAcademicos;

    private String carrera;

    private int añoIngreso;

    private int añoCursada;

    private int cantMateriasCursadas;

    private int cantMateriasAprobadas;

    private int cantMateriasQueCursa;

    private String persona;
}
