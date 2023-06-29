package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.DocenteDto;
import com.example.proyectoregistro.entities.Docente;
import com.example.proyectoregistro.entities.DocenteXMateria;
import com.example.proyectoregistro.entities.Materia;
import com.example.proyectoregistro.repository.DocenteRepository;
import com.example.proyectoregistro.repository.DocenteXMateriaRepository;
import com.example.proyectoregistro.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DocenteService implements IDocenteService{

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteXMateriaRepository docenteXMateriaRepository;

    public List<DocenteDto> getDocentesXMateria(String materia) {
        List<Docente> docentes = docenteRepository.findAll();
        List<DocenteXMateria> docenteXMaterias = docenteXMateriaRepository.findAll();

        List<Docente> docetesDeLaMateria = new ArrayList<>();

        for (int i = 0; i < docentes.size(); i++) {
            for (int j = 0; j < docenteXMaterias.size(); j++) {
                if(Objects.equals(docentes.get(i).getDocenteXMaterias().get(j).getMateria().getNombre(), materia)){
                    docetesDeLaMateria.add(docentes.get(i));
                }
            }
        }

        List<DocenteDto> docenteDtos = new ArrayList<>();

        for (Docente d: docetesDeLaMateria) {
            DocenteDto docenteDto = new DocenteDto();
            docenteDto.setNombre(d.getNombre());
            docenteDto.setApellido(d.getApellido());
            docenteDto.setEmail(d.getEmail());
            docenteDto.setTelefono(d.getTelefono());

            docenteDtos.add(docenteDto);
        }
        return docenteDtos;

    }
}
