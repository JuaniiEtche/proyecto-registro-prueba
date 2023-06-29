package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Persona;
import com.example.proyectoregistro.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByIdUser(long IdUsuario);

    Usuario findUsuariosByNombre(String nombre);

    @Query(value = "select new Persona (p.idPersona,p.nombre,p.apellido,p.dni,p.email,p.numTelefono,p.estado,p.legajo) from Usuario u inner join Persona p on p.usuario.idUser=u.idUser " +
            " inner join UsuarioXLinea ul on u.idUser=ul.usuario.idUser" +
            " inner join LineaInvestigacion li on li.idLinea=ul.lineaInvestigacion.idLinea " +
            " where li.nombre=:nombreLinea")
    List<Persona> getUsuarioLinea(@Param("nombreLinea") String nombreLinea);

    Usuario findUsuarioByPersona_IdPersona(long idPersona);

}