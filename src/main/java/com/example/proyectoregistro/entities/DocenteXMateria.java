package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "docentexmateria")
@Getter @Setter
public class DocenteXMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocenteXMateria")
    private long idDocenteXMateria;

    @ManyToOne(fetch = FetchType.EAGER)
    private Materia materia;

    @ManyToOne(fetch = FetchType.EAGER)
    private Docente docente;
}
