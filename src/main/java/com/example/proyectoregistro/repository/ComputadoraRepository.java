package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Computadora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputadoraRepository extends JpaRepository<Computadora, Long> {
}