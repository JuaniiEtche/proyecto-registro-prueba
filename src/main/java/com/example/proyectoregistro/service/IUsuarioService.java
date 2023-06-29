package com.example.proyectoregistro.service;


import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    void addUser(Usuario usuario);

    Usuario findUserByUsername(String nombre);

    List<Usuario> findAllUsers();

    void delete(Usuario usuario);

    List<Persona> findAllUsersXLinea(String nombreLinea);
}
