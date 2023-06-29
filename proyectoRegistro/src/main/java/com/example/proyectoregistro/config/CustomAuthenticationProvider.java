package com.example.proyectoregistro.config;

import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Usuario usuario = usuarioService.findUserByUsername(authentication.getName());

        if(usuario.getPersona().isEstado()) {
            if (usuario != null) {
                if (passwordEncoder.matches(authentication.getCredentials().toString(), usuario.getContraseña())) {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(usuario.getRole().getNombre()));

                    return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, grantedAuthorities);
                } else {
                    throw new BadCredentialsException("Wrong Password");
                }
            } else {
                throw new BadCredentialsException("Wrong UserName");
            }
        }else {
            throw new BadCredentialsException("Usuario no habilitado");
        }
    }

    public boolean supports(Class<?> authentication) {
        return false;
    }

}