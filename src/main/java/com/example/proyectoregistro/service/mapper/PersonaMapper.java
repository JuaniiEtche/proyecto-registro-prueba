package com.example.proyectoregistro.service.mapper;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.entities.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper implements IPersonaMapper{

    public Persona map(PersonaDto personaDto) {
        Persona persona = new Persona();

        persona.setIdPersona(personaDto.getIdPersona());
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setDni(personaDto.getDni());
        persona.setEmail(personaDto.getEmail());
        persona.setNumTelefono(personaDto.getNumTelefono());

        return persona;
    }

    public PersonaDto map(Persona persona) {
        PersonaDto personaDto = new PersonaDto();

        personaDto.setIdPersona(persona.getIdPersona());
        personaDto.setNombre(persona.getNombre());
        personaDto.setApellido(persona.getApellido());
        personaDto.setDni(persona.getDni());
        personaDto.setNumTelefono(persona.getNumTelefono());
        personaDto.setEmail(persona.getEmail());

        return personaDto;
    }
}
