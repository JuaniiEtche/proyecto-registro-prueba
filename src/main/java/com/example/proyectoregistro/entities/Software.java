package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "software")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSoftware")
    private long idSoftware;

    @Column(name = "so")
    private String so;

    @OneToMany(mappedBy = "software",cascade = CascadeType.ALL)
    private List<ProgramaXComputadora> programaXComputadoras;

    @OneToMany(mappedBy = "software",cascade = CascadeType.ALL)
    private List<Computadora> computadoras;
}
