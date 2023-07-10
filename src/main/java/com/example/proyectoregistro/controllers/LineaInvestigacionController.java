package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.LineaInvestigacionDto;
import com.example.proyectoregistro.repository.LineaInvestigacionRepository;
import com.example.proyectoregistro.service.ILineaInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.proyectoregistro.entities.LineaInvestigacion;

import java.util.List;

@RestController
public class LineaInvestigacionController {


/*Requestbody transforma el JSON como petición en el body como objeto de linea de investigación
* Siempre que se le manda algo es POST*/

    @Autowired
    private ILineaInvestigacionService lineaInvestigacionService;
    @PostMapping("/admin/lineasinvestigacion")
    public ResponseEntity<Void> registrarLinea(@RequestBody LineaInvestigacion lineaInvestigacion){

        /*Cuando se le pasa algo por el body, es privado, desde el fonrt no se va a saber, por el header es publico*/

        lineaInvestigacionService.agregarLinea(lineaInvestigacion);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/user/lineaInvestigacion")
    public ResponseEntity<List<LineaInvestigacionDto>> getLineas() {
        return ResponseEntity.status(HttpStatus.OK).body(lineaInvestigacionService.getLineas());
    }

    @GetMapping("/admin/lineaInvestigacion")
    public ResponseEntity<List<LineaInvestigacionDto>> getLineasAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(lineaInvestigacionService.getLineas());
    }
}

