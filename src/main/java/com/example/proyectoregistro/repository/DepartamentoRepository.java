package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}