package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.repository.PersonaRepository;
import com.example.proyectoregistro.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    public void addPersona(Persona persona) {
        personaRepository.save(persona);
    }

    public List<PersonaDto> traerBecarios() {

        List<Persona> personas = personaRepository.findPersonasByUsuario_Role_Nombre("user");
        List<PersonaDto> personaDtos = new ArrayList<>();

        for (Persona p:personas) {
            PersonaDto personaDto = new PersonaDto(p.getIdPersona(),p.getNombre(),p.getApellido(),p.getDni(),p.getEmail(),p.getNumTelefono(),p.getLegajo());
            personaDtos.add(personaDto);
        }

        return personaDtos;
    }
}
