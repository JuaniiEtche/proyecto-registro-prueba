package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Docente;
import com.example.proyectoregistro.entities.GabineteXReserva;
import com.example.proyectoregistro.entities.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ReservaDto {

    private long idReserva;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private String estado;

    private String nombreMateria;

    private String ApellidoDocente;

    private List<String> nombreGabinete;
    private String nombreDepartamento;

    private String nombreDocente;
    private String email;

    private String telefono;
}
