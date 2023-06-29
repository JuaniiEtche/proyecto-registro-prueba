package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.entities.Persona;

import java.util.List;

public interface IPersonaService {
    void addPersona(Persona persona);

    List<PersonaDto> traerBecarios();
}
