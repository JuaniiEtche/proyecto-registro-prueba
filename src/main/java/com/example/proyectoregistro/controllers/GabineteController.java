package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.GabineteDto;
import com.example.proyectoregistro.service.IGabineteService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GabineteController {

    @Autowired
    private IGabineteService gabineteService;

    @GetMapping("/admin/gabinete")
    public ResponseEntity<List<GabineteDto>> getGabinete(){
        return ResponseEntity.status(HttpStatus.OK).body(gabineteService.getGabinete());
    }

}
