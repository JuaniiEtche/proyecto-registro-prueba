package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "idRol",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}

