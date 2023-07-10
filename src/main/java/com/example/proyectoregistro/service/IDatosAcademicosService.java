package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DatosAcademicosDto;

public interface IDatosAcademicosService {
    DatosAcademicosDto buscarDatos(long idPersona);
}
