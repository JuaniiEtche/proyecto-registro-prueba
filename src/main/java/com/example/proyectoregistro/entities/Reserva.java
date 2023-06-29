package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Getter @Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private long idReserva;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "estado")
    //Estados: Pendiente,confirmada,cancelada,rechazada
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Materia materia;

    @ManyToOne(fetch = FetchType.EAGER)
    private Docente docente;

    @OneToMany(mappedBy = "reserva",cascade = CascadeType.ALL)
    private List<GabineteXReserva> gabineteXReservas;
}
