package com.example.proyectoregistro.repository;

        import com.example.proyectoregistro.dto.LineaResponse;
        import com.example.proyectoregistro.entities.LineaInvestigacion;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;
        import com.example.proyectoregistro.dto.PersonaDto;


        import java.util.List;

@Repository
@Transactional
public interface LineaInvestigacionRepository extends JpaRepository<LineaInvestigacion, Long> {

    @Query("SELECT DISTINCT li FROM LineaInvestigacion li " +
            "JOIN li.lineas ul " +
            "JOIN ul.usuario u " +
            "WHERE u.nombre = :nombreUsuario")
    List<LineaInvestigacion> getLineasPorUsuario(@Param("nombreUsuario") String nombreUsuario);
}