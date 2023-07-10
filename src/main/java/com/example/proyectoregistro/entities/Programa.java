package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "programa")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrograma")
    private long idPrograma;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "programa",cascade = CascadeType.ALL)
    private List<ProgramaXComputadora> programaXComputadoras;
}
