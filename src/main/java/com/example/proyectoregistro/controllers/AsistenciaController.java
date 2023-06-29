package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.AsistenciaDto;
import com.example.proyectoregistro.entities.Asistencia;
import com.example.proyectoregistro.service.AsistenciaService;
import com.example.proyectoregistro.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @GetMapping("/user/asistencia/{nombreUsuario}")
    public List<AsistenciaDto> getAsistencias(@PathVariable String nombreUsuario){
        return asistenciaService.getAsistencias(nombreUsuario);
    }

    @PostMapping("/user/asistencia/{nombreUsuario}")
    public void registrarAsistencias(@PathVariable String nombreUsuario, @RequestBody Asistencia asistencia){
        asistenciaService.registrar(nombreUsuario,asistencia);
    }

    @GetMapping("/admin/asistencia/{idPersona}")
    public List<AsistenciaDto> getAsistenciasBecario(@PathVariable long idPersona){
        return asistenciaService.getAsistenciasBecario(idPersona);
    }
}
