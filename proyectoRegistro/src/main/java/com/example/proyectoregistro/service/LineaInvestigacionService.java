package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.LineaInvestigacionDto;
import com.example.proyectoregistro.dto.LineaResponse;
import com.example.proyectoregistro.dto.LineaResponseDto;
import com.example.proyectoregistro.dto.PersonaDto;
import com.example.proyectoregistro.entities.LineaInvestigacion;
import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.entities.UsuarioXLinea;
import com.example.proyectoregistro.repository.LineaInvestigacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service /*Te deja instanciar el servicio del interfeaz*/
public class LineaInvestigacionService implements ILineaInvestigacionService{

    @Autowired /*Se usa cuando quiera instanciar una
     vinterfaz*/
    private LineaInvestigacionRepository lineaInvestigacionRepository; /*Se está instanciando el Repository para poder utilizarlo en las operaciones*/
    public void agregarLinea(LineaInvestigacion lineaInvestigacion) {
        lineaInvestigacionRepository.save(lineaInvestigacion);/*Le paso la instancia que deseo guardar en la DB*/
    }

    public List<LineaResponseDto> getUsersLinea(String nombreUsuario) {
        List<LineaInvestigacion> lineas = lineaInvestigacionRepository.getLineasPorUsuario(nombreUsuario);
        List<LineaResponseDto> lineasFiltradas = new ArrayList<>();

        for (LineaInvestigacion linea : lineas) {
            List<Persona> personas = linea.getLineas()
                    .stream()
                    .map(UsuarioXLinea::getUsuario)
                    .filter(persona -> persona.getRole().getNombre().equals("user"))  // Filtrar por rol "user"
                    .map(Usuario::getPersona)
                    .collect(Collectors.toList());

            List<PersonaDto> personaDtos = personas.stream()
                    .map(this::convertToPersonaDto)
                    .collect(Collectors.toList());

            lineasFiltradas.add(new LineaResponseDto(linea.getNombre(), personaDtos));
        }

        return lineasFiltradas;
    }

    public List<LineaInvestigacionDto> getLineas() {
        List<LineaInvestigacion> lineas = lineaInvestigacionRepository.findAll();

        List<LineaInvestigacionDto> lineaInvestigacionDtos = new ArrayList<>();

        for (LineaInvestigacion linea: lineas) {
            LineaInvestigacionDto lineaInvestigacionDto = new LineaInvestigacionDto(linea.getNombre(),linea.getDescripcion());
            lineaInvestigacionDtos.add(lineaInvestigacionDto);
        }
        return lineaInvestigacionDtos;
    }

    private PersonaDto convertToPersonaDto(Persona persona) {
        PersonaDto personaDto = new PersonaDto();
        personaDto.setIdPersona(persona.getIdPersona());
        personaDto.setNombre(persona.getNombre());
        personaDto.setApellido(persona.getApellido());
        personaDto.setDni(persona.getDni());
        personaDto.setEmail(persona.getEmail());
        personaDto.setNumTelefono(persona.getNumTelefono());
        personaDto.setLegajo(persona.getLegajo());
        return personaDto;
    }

}
