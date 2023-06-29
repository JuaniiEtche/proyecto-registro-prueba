package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findRolByNombre(String nombre);
}