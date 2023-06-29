package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="persona")
@Getter @Setter
public class Persona {

    public Persona(long idPersona, String nombre, String apellido, int dni, String email, long numTelefono, boolean estado, int legajo) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.numTelefono = numTelefono;
        this.estado = estado;
        this.legajo = legajo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private long idPersona;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellido",nullable = false)
    private String apellido;

    @Column(name = "dni",nullable = false)
    private int dni;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "numTelefono",nullable = false)
    private long numTelefono;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @Column(name = "legajo",nullable = false)
    private int legajo;

    @OneToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL)
    private List<PersonaXGabinete> personaXGabinetes;

    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;

    public Persona() {

    }
}
