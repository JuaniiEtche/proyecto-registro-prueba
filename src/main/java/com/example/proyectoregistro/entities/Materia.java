package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "materia")
@Getter @Setter
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMateria")
    private long idMateria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    private Departamento departamento;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    private List<DocenteXMateria> docenteXMaterias;
}
