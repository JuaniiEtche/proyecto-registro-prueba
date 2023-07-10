package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ProyectoDto;
import com.example.proyectoregistro.entities.Proyecto;
import com.example.proyectoregistro.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProyectoService implements IProyectoService{

    @Autowired
    private ProyectoRepository proyectoRepository;


    public List<ProyectoDto> getProyectoPersona(long idPersona) {
        List<Proyecto> proyectos = proyectoRepository.buscarProyectoPorIdPersona(idPersona);

        List<ProyectoDto> proyectoDtos = new ArrayList<>();

        for (Proyecto p: proyectos) {
            ProyectoDto proyectoDto= new ProyectoDto(p.getIdProyecto(),p.getNombre(),p.getDescripcion(),p.getFechaInicio(),p.getFechaFin(),p.getLineaInvestigacion().getNombre());
            proyectoDtos.add(proyectoDto);
        }

        return proyectoDtos;
    }
}
