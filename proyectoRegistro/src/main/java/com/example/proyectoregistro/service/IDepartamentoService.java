package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DepartamentoDto;
import com.example.proyectoregistro.entities.Departamento;

import java.util.List;

public interface IDepartamentoService {
    List<DepartamentoDto> getDepartamentos();
}
