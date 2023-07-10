package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareRepository extends JpaRepository<Software, Long> {
}