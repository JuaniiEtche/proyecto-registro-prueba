package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.LineaInvestigacion;
import com.example.proyectoregistro.entities.Usuario;
import com.example.proyectoregistro.entities.UsuarioXLinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioXLineaRepository extends JpaRepository<UsuarioXLinea, Long> {

    void deleteUsuarioXLineaByUsuario_idUserAndLineaInvestigacion_Nombre(long idUsuario, String nombreLinea);
}