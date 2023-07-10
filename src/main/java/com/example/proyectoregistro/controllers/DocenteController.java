package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.DocenteDto;
import com.example.proyectoregistro.service.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocenteController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/admin/docente/{materia}")
    public ResponseEntity<List<DocenteDto>> getDocentesXMateria(@PathVariable String materia){
        return ResponseEntity.status(HttpStatus.OK).body(docenteService.getDocentesXMateria(materia));
    }
}
