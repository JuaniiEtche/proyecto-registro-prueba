package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ProyectoDto;

import java.util.List;

public interface IProyectoService {
    List<ProyectoDto> getProyectoPersona(long idPersona);
}
