package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "docente")
@Getter @Setter
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocente")
    private long idDocente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "docente",cascade = CascadeType.ALL)
    private List<DocenteXMateria> docenteXMaterias;
}
