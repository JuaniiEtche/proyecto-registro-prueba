package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.GabineteDto;
import com.example.proyectoregistro.entities.Gabinete;
import com.example.proyectoregistro.repository.GabineteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GabineteService implements IGabineteService{

    @Autowired
    private GabineteRepository gabineteRepository;

    public List<GabineteDto> getGabinete() {
        List<Gabinete> gabinetes = gabineteRepository.findAll();

        List<GabineteDto> gabineteDtos = new ArrayList<>();
        for (Gabinete g:gabinetes) {
            GabineteDto gabineteDto = new GabineteDto();
            gabineteDto.setNombre(g.getNombre());
            gabineteDto.setCantidadEquipos(g.getCantidadEquipos());
            gabineteDtos.add(gabineteDto);
        }
        return gabineteDtos;
    }
}
