package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.Gabinete;
import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.PersonaXGabinete;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.repository.GabineteRepository;
import com.example.proyectoregistro.repository.PersonaXGabineteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaXGabineteService implements IPersonaXGabineteService{

    @Autowired
    private PersonaXGabineteRepository personaXGabineteRepository;
    @Autowired
    private GabineteRepository gabineteRepository;

    public void addPersona(Persona persona) {
        List<Gabinete> gabinetes = gabineteRepository.findAll();
        for (Gabinete g:gabinetes) {
            PersonaXGabinete pxg = new PersonaXGabinete();
            pxg.setGabinete(g);
            pxg.setPersona(persona);
            personaXGabineteRepository.save(pxg);
        }
    }
}
