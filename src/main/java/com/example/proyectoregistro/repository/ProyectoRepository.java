package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query("select new Proyecto (p.idProyecto,p.nombre,p.descripcion,p.fechaInicio,p.fechaFin,p.lineaInvestigacion) from Proyecto p inner join LineaInvestigacion li " +
            "on p.lineaInvestigacion.idLinea=li.idLinea inner join UsuarioXLinea ul " +
            "on li.idLinea=ul.lineaInvestigacion.idLinea inner join Usuario u " +
            "on u.idUser=ul.usuario.idUser inner join Persona per " +
            "on per.idPersona=u.persona.idPersona " +
            "where per.idPersona=:idPersona")
    List<Proyecto> buscarProyectoPorIdPersona(@Param("idPersona") long idPersona);
}