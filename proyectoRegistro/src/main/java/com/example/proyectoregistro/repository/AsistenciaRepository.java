package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findAsistenciasByPersona_Usuario_Nombre(String nombreUsuario);

    List<Asistencia> findAsistenciasByPersona_IdPersonaAndPersona_Usuario_Role_Nombre(long idPersona,String nombreRol);
}