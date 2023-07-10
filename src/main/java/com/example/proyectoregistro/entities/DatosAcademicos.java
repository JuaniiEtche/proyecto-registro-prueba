package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "datosAcademicos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosAcademicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDatosAcademicos")
    private long idDatosAcademicos;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "añoIngreso")
    private int añoIngreso;

    @Column(name = "añoCursada")
    private int añoCursada;

    @Column(name = "cantMateriasCursadas")
    private int cantMateriasCursadas;

    @Column(name = "cantMateriasAprobadas")
    private int cantMateriasAprobadas;

    @Column(name = "cantMateraisQueCursa")
    private int cantMateriasQueCursa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

}
