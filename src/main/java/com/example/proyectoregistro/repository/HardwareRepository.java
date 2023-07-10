package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}