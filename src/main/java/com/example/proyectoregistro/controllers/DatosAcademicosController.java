package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.DatosAcademicosDto;
import com.example.proyectoregistro.entities.DatosAcademicos;
import com.example.proyectoregistro.service.DatosAcademicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatosAcademicosController {

    @Autowired
    private DatosAcademicosService datosAcademicosService;

    @GetMapping("/admin/datosacademicos/{idPersona}")
    public ResponseEntity<DatosAcademicosDto> getDatosAcademicosPersona(@PathVariable long idPersona){
        return ResponseEntity.status(HttpStatus.OK).body(datosAcademicosService.buscarDatos(idPersona));
    }
}
