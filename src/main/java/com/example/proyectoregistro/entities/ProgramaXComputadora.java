package com.example.proyectoregistro.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "programaxcomputadora")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaXComputadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProgramaXComputadora")
    private long idProgramaXComputadora;

    @ManyToOne(fetch = FetchType.EAGER)
    private Programa programa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Software software;
}
