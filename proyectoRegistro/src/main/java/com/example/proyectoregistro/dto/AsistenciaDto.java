package com.example.proyectoregistro.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
public class AsistenciaDto {

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
