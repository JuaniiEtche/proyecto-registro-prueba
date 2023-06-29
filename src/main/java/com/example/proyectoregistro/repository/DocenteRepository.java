package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Docente findDocenteByApellido(String nombre);
}