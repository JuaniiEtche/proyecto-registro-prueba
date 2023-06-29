package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "gabinete")
@Getter @Setter
public class Gabinete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGabinete")
    private long idGabinete;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidadEquipos")
    private int cantidadEquipos;

    @OneToMany(mappedBy = "gabinete",cascade = CascadeType.ALL)
    private List<PersonaXGabinete> personaXGabinetes;

    @OneToMany(mappedBy = "gabinete",cascade = CascadeType.ALL)
    private List<GabineteXReserva> gabineteXReservas;
}
