package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personaxgabinete")
@Getter @Setter
public class PersonaXGabinete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonaxgabinete",nullable = false)
    private long idPersonaXGabinete;

    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gabinete gabinete;

}
