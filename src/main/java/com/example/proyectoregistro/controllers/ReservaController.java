package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.ReservaDto;
import com.example.proyectoregistro.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping("/admin/reserva")
    public void addReserva(@RequestBody ReservaDto reservaDto){
        reservaService.addReserva(reservaDto);
    }

    @GetMapping("/admin/reservas-confirmadas")
    public List<ReservaDto> getReservaConfirmadas(){
        return reservaService.getReservasConfirmadas();
    }
}
