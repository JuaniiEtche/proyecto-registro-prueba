package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.service.IUsuarioXLineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioXLineaController {

    @Autowired
    private IUsuarioXLineaService usuarioXLineaService;

    @DeleteMapping(value = "/admin/usuarioxlinea/{idPersona}/{nombreLinea}")
    public ResponseEntity<Void> eliminarUsuarioXLinea(@PathVariable long idPersona, @PathVariable String nombreLinea){
        usuarioXLineaService.eliminarConexion(idPersona,nombreLinea);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
