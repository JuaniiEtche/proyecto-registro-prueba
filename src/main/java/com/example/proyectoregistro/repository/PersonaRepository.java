package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Persona findPersonaByUsuario_Nombre(String nombre);

    List<Persona> findPersonasByUsuario_Role_Nombre(String nombreRol);

    Persona findPersonaByIdPersona(long idPersona);
}