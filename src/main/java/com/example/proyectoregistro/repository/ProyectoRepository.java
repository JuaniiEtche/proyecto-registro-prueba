package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}