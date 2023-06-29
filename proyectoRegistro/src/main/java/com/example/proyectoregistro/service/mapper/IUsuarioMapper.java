package com.example.proyectoregistro.service.mapper;

import com.example.proyectoregistro.dto.UsuarioDto;
import com.example.proyectoregistro.entities.Usuario;

public interface IUsuarioMapper {

    Usuario map(UsuarioDto usuarioDto);

    UsuarioDto map(Usuario usuario);
}
