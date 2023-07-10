package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.ProyectoDto;
import com.example.proyectoregistro.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;

    @GetMapping("/admin/proyecto/{idPersona}")
    public ResponseEntity<List<ProyectoDto>> getProyecto(@PathVariable long idPersona){
        return ResponseEntity.status(HttpStatus.OK).body(proyectoService.getProyectoPersona(idPersona));
    }
}
