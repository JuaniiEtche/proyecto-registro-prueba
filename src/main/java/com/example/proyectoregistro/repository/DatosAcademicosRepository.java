package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.DatosAcademicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosAcademicosRepository extends JpaRepository<DatosAcademicos, Long> {

    DatosAcademicos findDatosAcademicosByPersona_IdPersona(long idPersona);
}