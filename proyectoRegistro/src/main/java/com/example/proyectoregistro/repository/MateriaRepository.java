package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findMateriaByNombre(String nombre);
}