package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.AsistenciaDto;
import com.example.proyectoregistro.entities.Asistencia;

import java.util.List;

public interface IAsistenciaService {
    List<AsistenciaDto> getAsistencias(String nombreUsuario);

    void registrar(String nombreUsuario, Asistencia asistencia);

    List<AsistenciaDto> getAsistenciasBecario(long idPersona);
}
