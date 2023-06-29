package com.example.proyectoregistro.service;

import com.example.proyectoregistro.entities.LineaInvestigacion;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.repository.LineaInvestigacionRepository;
import com.example.proyectoregistro.repository.PersonaRepository;
import com.example.proyectoregistro.repository.UsuarioRepository;
import com.example.proyectoregistro.repository.UsuarioXLineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioXLineaService implements IUsuarioXLineaService{

    @Autowired
    private UsuarioXLineaRepository usuarioXLineaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void eliminarConexion(long idPersona, String nombreLinea) {

        Usuario usuario = usuarioRepository.findUsuarioByPersona_IdPersona(idPersona);

        usuarioXLineaRepository.deleteUsuarioXLineaByUsuario_idUserAndLineaInvestigacion_Nombre(usuario.getIdUser(),nombreLinea);
    }
}
