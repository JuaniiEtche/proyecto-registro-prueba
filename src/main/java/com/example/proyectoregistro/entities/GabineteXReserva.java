package com.example.proyectoregistro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gabinetexreserva")
@Getter @Setter
public class GabineteXReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgabinetexreserva")
    private long idGabineteXReserva;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gabinete gabinete;

    @ManyToOne(fetch = FetchType.EAGER)
    private Reserva reserva;
}
