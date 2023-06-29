package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ReservaDto;
import com.example.proyectoregistro.entities.*;
import com.example.proyectoregistro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService implements IReservaService{

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private GabineteRepository gabineteRepository;
    @Autowired
    private GabineteXReservaRepository gabineteXReservaRepository;
    public void addReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();

        Materia m = materiaRepository.findMateriaByNombre(reservaDto.getNombreMateria());
        reserva.setMateria(m);
        Docente d = docenteRepository.findDocenteByApellido(reservaDto.getApellidoDocente());
        reserva.setDocente(d);

        reserva.setEstado("confirmada");
        reserva.setFecha(reservaDto.getFecha());
        reserva.setHoraInicio(reservaDto.getHoraInicio());
        reserva.setHoraFin(reservaDto.getHoraFin());
        reservaRepository.save(reserva);

        List<GabineteXReserva> lista = new ArrayList<>();
        for (int i = 0; i < reservaDto.getNombreGabinete().size(); i++) {
            GabineteXReserva gxr = new GabineteXReserva();
            Gabinete g = gabineteRepository.findGabineteByNombre(reservaDto.getNombreGabinete().get(i));
            gxr.setGabinete(g);
            gxr.setReserva(reserva);
            gabineteXReservaRepository.save(gxr);

            lista.add(gxr);
        }

        reserva.setGabineteXReservas(lista);

        reservaRepository.save(reserva);
    }

    public List<ReservaDto> getReservasConfirmadas() {
        List<Reserva> reservas = reservaRepository.findReservasByEstado("confirmada");

        List<ReservaDto> dtos = new ArrayList<>();

        for (Reserva r:reservas) {
            ReservaDto reservaDto = new ReservaDto();

            reservaDto.setEstado(r.getEstado());
            reservaDto.setFecha(r.getFecha());
            reservaDto.setHoraInicio(r.getHoraInicio());
            reservaDto.setHoraFin(r.getHoraFin());
            reservaDto.setApellidoDocente(r.getDocente().getApellido());
            reservaDto.setNombreDocente(r.getDocente().getApellido());
            reservaDto.setNombreDocente(r.getDocente().getNombre());
            reservaDto.setNombreDepartamento(r.getMateria().getDepartamento().getNombre());
            reservaDto.setNombreMateria(r.getMateria().getNombre());
            reservaDto.setEmail(r.getDocente().getEmail());
            reservaDto.setTelefono(r.getDocente().getTelefono());
            List<String> gab = new ArrayList<>();

            for (int i = 0; i < r.getGabineteXReservas().size(); i++) {
                gab.add(r.getGabineteXReservas().get(i).getGabinete().getNombre());
            }
            reservaDto.setNombreGabinete(gab);
            dtos.add(reservaDto);
        }
        return dtos;
    }
}
