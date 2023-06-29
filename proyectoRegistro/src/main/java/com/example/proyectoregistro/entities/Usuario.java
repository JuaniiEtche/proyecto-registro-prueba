package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter @Setter
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario",nullable = false)
    private long idUser;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "contraseña",nullable = false)
    private String contraseña;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rol role;

    @OneToOne(fetch = FetchType.EAGER)
    private Persona persona;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<UsuarioXLinea> lineas;

}
