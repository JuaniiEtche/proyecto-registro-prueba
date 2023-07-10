package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/admin/personas")
    public ResponseEntity<List<PersonaDto>> getPersonas(){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.traerBecarios());
    }

    @GetMapping("/admin/personas/{idPersona}")
    public ResponseEntity<PersonaDto> getPersona(@PathVariable long idPersona){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.buscarBecario(idPersona));
    }
}
