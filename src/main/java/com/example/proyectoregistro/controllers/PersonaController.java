package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/admin/personas")
    public List<PersonaDto> getPersonas(){
        return personaService.traerBecarios();
    }
}
