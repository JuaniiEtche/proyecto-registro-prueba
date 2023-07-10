package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.AsistenciaDto;
import com.example.proyectoregistro.entities.Asistencia;
import com.example.proyectoregistro.service.AsistenciaService;
import com.example.proyectoregistro.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @GetMapping("/user/asistencia/{nombreUsuario}")
    public ResponseEntity<List<AsistenciaDto>> getAsistencias(@PathVariable String nombreUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(asistenciaService.getAsistencias(nombreUsuario));
    }

    @PostMapping("/user/asistencia/{nombreUsuario}")
    public ResponseEntity<Void> registrarAsistencias(@PathVariable String nombreUsuario, @RequestBody Asistencia asistencia){
        asistenciaService.registrar(nombreUsuario,asistencia);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admin/asistencia/{idPersona}")
    public ResponseEntity<List<AsistenciaDto>> getAsistenciasBecario(@PathVariable long idPersona){
        return ResponseEntity.status(HttpStatus.OK).body(asistenciaService.getAsistenciasBecario(idPersona));
    }
}
