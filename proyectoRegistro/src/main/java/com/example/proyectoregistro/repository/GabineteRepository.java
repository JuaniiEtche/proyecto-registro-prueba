package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Gabinete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GabineteRepository extends JpaRepository<Gabinete, Long> {

    Gabinete findGabineteByNombre(String nombre);
}