package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "computadora")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Computadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComputadora")
    private long idComputadora;

    @ManyToOne(fetch = FetchType.EAGER)
    private Software software;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hardware hardware;
}
