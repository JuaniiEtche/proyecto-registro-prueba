package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.GabineteXReserva;
import com.example.proyectoregistro.entities.PersonaXGabinete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GabineteDto {

    @Column(name = "nombre")
    private String nombre;

    private int cantidadEquipos;

}
