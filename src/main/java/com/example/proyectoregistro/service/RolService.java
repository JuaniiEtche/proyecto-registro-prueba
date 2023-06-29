package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.Rol;
import com.example.proyectoregistro.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService{

    @Autowired
    private RolRepository rolRepository;

    public Rol buscarRolPorNombre(String nombreRol) {
        return rolRepository.findRolByNombre(nombreRol);
    }
}
