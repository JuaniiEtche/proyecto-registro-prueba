package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DepartamentoDto;
import com.example.proyectoregistro.entities.Departamento;
import com.example.proyectoregistro.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService{

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoDto> getDepartamentos() {
        List <Departamento> departamentos = departamentoRepository.findAll();
        List <DepartamentoDto> departamentoDtos = new ArrayList<>();

        for (Departamento d: departamentos) {
            DepartamentoDto departamentoDto = new DepartamentoDto();
            List <String> nombres = new ArrayList<>();

            departamentoDto.setNombre(d.getNombre());
            for (int i = 0; i <d.getMaterias().size() ; i++) {
                nombres.add(d.getMaterias().get(i).getNombre());
            }
            departamentoDto.setNombreMaterias(nombres);
            departamentoDtos.add(departamentoDto); // Agregar departamentoDto a la lista
        }
        return departamentoDtos;
    }
}
