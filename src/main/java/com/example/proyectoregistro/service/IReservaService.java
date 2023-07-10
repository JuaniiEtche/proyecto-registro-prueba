package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ReservaDto;

import java.util.List;

public interface IReservaService {
    List<ReservaDto> tablaReservaPendiente();

    void addReserva(ReservaDto reservaDto);

    List<ReservaDto> getReservasConfirmadas();
}
