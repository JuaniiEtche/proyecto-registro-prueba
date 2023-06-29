package com.example.proyectoregistro.service.mapper;

import com.example.proyectoregistro.dto.UsuarioDto;
import com.example.proyectoregistro.entities.Rol;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper implements IUsuarioMapper {

    @Autowired
    private RolRepository rolRepository;

    public Usuario map(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setIdUser(usuarioDto.getIdUsuario());
        usuario.setNombre(usuarioDto.getNombre());
        if(usuarioDto.getRol() != null){
            Rol rol=rolRepository.findRolByNombre(usuarioDto.getRol());
            usuario.setRole(rol);
        }

        return usuario;
    }

    public UsuarioDto map(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setRol(usuario.getRole().getNombre());
        return usuarioDto;
    }
}
