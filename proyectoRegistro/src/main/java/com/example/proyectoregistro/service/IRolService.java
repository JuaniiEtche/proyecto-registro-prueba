package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.Rol;
import org.springframework.stereotype.Service;

public interface IRolService {
    Rol buscarRolPorNombre(String nombreRol);
}
