package com.example.proyectoregistro.dto;

import com.example.proyectoregistro.entities.Materia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class DepartamentoDto {

    private String nombre;

    private List<String> nombreMaterias;
}
