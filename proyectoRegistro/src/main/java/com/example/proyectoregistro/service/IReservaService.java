package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ReservaDto;

import java.util.List;

public interface IReservaService {

    void addReserva(ReservaDto reservaDto);

    List<ReservaDto> getReservasConfirmadas();
}
