package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DocenteDto;

import java.util.List;

public interface IDocenteService {
    List<DocenteDto> getDocentesXMateria(String materia);
}
