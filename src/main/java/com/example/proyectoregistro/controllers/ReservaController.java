package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.dto.ReservaDto;
import com.example.proyectoregistro.service.IAsistenciaService;
import com.example.proyectoregistro.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.proyectoregistro.service.IReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservaController {

    @Autowired
    private IReservaService reservaService;


    @GetMapping("/admin/reservas-pendientes")
    public List<ReservaDto> retornoPendientes () {
        return reservaService.tablaReservaPendiente(); //Devuelve una array de reservas pendientes
    }

    @PostMapping("/admin/reserva")
    public ResponseEntity<Void> addReserva(@RequestBody ReservaDto reservaDto){
        reservaService.addReserva(reservaDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admin/reservas-confirmadas")
    public ResponseEntity<List<ReservaDto>> getReservaConfirmadas(){
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.getReservasConfirmadas());
    }

}
