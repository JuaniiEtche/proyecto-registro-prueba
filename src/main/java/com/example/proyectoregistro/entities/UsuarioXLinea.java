package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarioxlinea")
@Getter @Setter
public class UsuarioXLinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarioXLinea")
    private long idUsuarioXLinea;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private LineaInvestigacion lineaInvestigacion;
}
