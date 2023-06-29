package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LineaResponse {

    private String nombreLinea;

    public LineaResponse(String nombreLinea, List<Persona> personas) {
        this.nombreLinea = nombreLinea;
        this.personas = personas;
    }

    private List<Persona> personas;
}
