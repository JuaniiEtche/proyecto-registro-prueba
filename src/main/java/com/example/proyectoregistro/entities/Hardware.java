package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hardware")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHardware")
    private long idHardware;

    @Column(name ="procesador")
    private String procesador;

    @Column(name = "memoriaRam")
    private String memoriaRam;

    @Column(name = "almacenamiento")
    private String almacenamiento;

    @Column(name = "monitorDescripcion")
    private String monitorDescripcion;

    @OneToMany(mappedBy = "hardware",cascade = CascadeType.ALL)
    private List<Computadora> computadoras;
}
