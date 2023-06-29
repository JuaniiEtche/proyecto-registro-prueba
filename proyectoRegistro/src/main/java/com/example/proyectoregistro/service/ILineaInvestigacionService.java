package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.LineaInvestigacionDto;
import com.example.proyectoregistro.dto.LineaResponse;
import com.example.proyectoregistro.dto.LineaResponseDto;
import com.example.proyectoregistro.entities.LineaInvestigacion;

import java.util.List;

public interface ILineaInvestigacionService {
    /*especificar qué va a devoler, qué va a recibir pero no cómo va hacer
    En la Implementación le dice como
     */
    void agregarLinea(LineaInvestigacion lineaInvestigacion);

    List<LineaResponseDto> getUsersLinea(String nombreUsuario);

    List<LineaInvestigacionDto> getLineas();
}
