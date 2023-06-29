package com.example.proyectoregistro.service.mapper;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.entities.Persona;

public interface IPersonaMapper {

    Persona map(PersonaDto personaDto);

    PersonaDto map(Persona persona);
}
