package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    public void addUser(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findUserByUsername(String nombreUsuario){
        return usuarioRepository.findUsuariosByNombre(nombreUsuario);
    }

    public List<Usuario> findAllUsers(){
        return usuarioRepository.findAll();
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<Persona> findAllUsersXLinea(String nombreLinea) {
        return usuarioRepository.getUsuarioLinea(nombreLinea);
    }

}

