package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DatosAcademicosDto;
import com.example.proyectoregistro.entities.DatosAcademicos;
import com.example.proyectoregistro.repository.DatosAcademicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosAcademicosService implements IDatosAcademicosService{

    @Autowired
    private DatosAcademicosRepository datosAcademicosRepository;
    public DatosAcademicosDto buscarDatos(long idPersona) {

        DatosAcademicos d = datosAcademicosRepository.findDatosAcademicosByPersona_IdPersona(idPersona);

        return new DatosAcademicosDto(d.getIdDatosAcademicos(),d.getCarrera(),d.getAñoIngreso(),d.getAñoCursada(),d.getCantMateriasCursadas(),d.getCantMateriasAprobadas(),d.getCantMateriasQueCursa(),d.getPersona().getNombre()+" "+d.getPersona().getApellido());
    }
}
