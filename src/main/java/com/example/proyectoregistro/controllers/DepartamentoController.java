package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.DepartamentoDto;
import com.example.proyectoregistro.entities.Departamento;
import com.example.proyectoregistro.service.DepartamentoService;
import com.example.proyectoregistro.service.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/admin/departamento")
    public ResponseEntity<List<DepartamentoDto>> getNombresDepartamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.getDepartamentos());
    }
}
