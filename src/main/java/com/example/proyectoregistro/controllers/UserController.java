package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.*;
import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.Rol;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.repository.PersonaRepository;
import com.example.proyectoregistro.repository.RolRepository;
import com.example.proyectoregistro.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class UserController {
    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IPersonaService personaService;
    @Autowired
    IRolService rolService;
    @Autowired
    IPersonaXGabineteService personaXGabineteService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ILineaInvestigacionService lineaInvestigacionService;

    @PostMapping("/public/usuario")
    public void addUsuario(@RequestBody UsuarioRequest usuarioRequest){

        //CON DATOS DE LA PERSONA, CREO LA PERSONA
        Persona persona = new Persona();
        persona.setNombre(usuarioRequest.getNombrePersona());
        persona.setApellido(usuarioRequest.getApellido());
        persona.setDni(usuarioRequest.getDni());
        persona.setNumTelefono(usuarioRequest.getNumTelefono());
        persona.setEmail(usuarioRequest.getEmail());
        persona.setEstado(false);

        personaService.addPersona(persona);
        //CON DATOS DEL USUARIO CREO EL USUARIO Y LE ASIGNO LA PERSONA RECIEN CREADA
        Usuario usuario = new Usuario();

        usuario.setPersona(persona);
        usuario.setNombre(usuarioRequest.getNombreUsuario());

        String hash = passwordEncoder.encode(usuarioRequest.getContraseña());

        usuario.setContraseña(hash);

        usuarioService.addUser(usuario);

        Usuario usuario2 = usuarioService.findUserByUsername(usuarioRequest.getNombreUsuario());
        persona.setUsuario(usuario2);
        personaService.addPersona(persona);

    }

    @GetMapping("/user/{username}")
    public UsuarioDto getUserName(@PathVariable String username){
        Usuario u = usuarioService.findUserByUsername(username);
        return new UsuarioDto(u.getIdUser(),u.getNombre(),u.getRole().getNombre());
    }

    @GetMapping("/admin/user/{nombreUsuario}")
    public List<LineaResponseDto> getUsuarioXlinea(@PathVariable String nombreUsuario){

        List<LineaResponseDto> response = lineaInvestigacionService.getUsersLinea(nombreUsuario);

        return response;
    }
    @DeleteMapping("/admin/user")
    public void deleteUser(@RequestBody Usuario usuario){
        usuarioService.delete(usuario);
    }

}
