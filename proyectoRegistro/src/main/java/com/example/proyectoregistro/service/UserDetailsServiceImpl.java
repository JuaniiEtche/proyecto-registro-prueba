package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = getById(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return User
                .withUsername(username)
                .password(usuario.getContraseña())
                .roles(usuario.getRole().getNombre())
                .build();
    }

    public com.example.proyectoregistro.entities.Usuario getById(String username) {

        com.example.proyectoregistro.entities.Usuario usuario = usuarioService.findUserByUsername(username);

        if(usuario==null) return null;

        return usuario;
    }
}
