package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}