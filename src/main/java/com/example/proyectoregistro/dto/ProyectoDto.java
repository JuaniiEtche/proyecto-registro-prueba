package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.LineaInvestigacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDto {


    private long idProyecto;

    private String nombre;

    private String descripcion;

    private Date fechaInicio;

    private Date fechaFin;

    private String lineaInvestigacion;
}
