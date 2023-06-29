package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departamento")
@Getter @Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento")
    private long idDepartamento;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL)
    private List<Materia> materias;
}
