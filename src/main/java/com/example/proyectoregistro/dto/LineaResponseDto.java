package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Persona;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LineaResponseDto {
    private String nombreLinea;

    public LineaResponseDto() {
    }

    public LineaResponseDto(String nombreLinea, List<PersonaDto> personaDtos) {
        this.nombreLinea = nombreLinea;
        this.personaDtos = personaDtos;
    }

    private List<PersonaDto> personaDtos;
}
