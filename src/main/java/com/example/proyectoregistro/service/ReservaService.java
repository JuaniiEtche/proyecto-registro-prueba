package com.example.proyectoregistro.service;

import com.example.proyectoregistro.dto.ReservaDto;
import com.example.proyectoregistro.entities.Reserva;
import com.example.proyectoregistro.repository.ReservaRepository;
import com.example.proyectoregistro.entities.*;
import com.example.proyectoregistro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ReservaService implements IReservaService { //implementa la Interfaz

    public List<ReservaDto> tablaReservaPendiente() {
        List<Reserva> reservas = reservaRepository.findReservasByEstado("pendiente"); // Hace uso de la Query que está en Repository
        List<ReservaDto> reservaDtos = new ArrayList<>(); // Instancia para el mapeo

        for (Reserva R:reservas) { // a Reserva le das de comer los que salga de la query que está conectada al Repository
            ReservaDto reservaDto = new ReservaDto(); // Instancia pero está vacío
            reservaDto.setEstado(R.getEstado()); // Seteo a la ReservaDto el estado pendiente
            reservaDto.setFecha(R.getFecha());
            reservaDto.setApellidoDocente(R.getDocente().getApellido());
            reservaDto.setHoraFin(R.getHoraFin());
            reservaDto.setHoraInicio(R.getHoraInicio());
            reservaDto.setNombreMateria(R.getMateria().getNombre());
            reservaDto.setIdReserva(R.getIdReserva());
            reservaDto.setEmail(R.getDocente().getEmail());
            reservaDto.setTelefono(R.getDocente().getTelefono());
            //
            List<String> gabinetesString = new ArrayList<String>(); // Creo una lista porque los gabinetes con una Lista

            for (int i = 0; i < reservas.size(); i++) {
                gabinetesString.add(R.getGabineteXReservas().get(i).getGabinete().getNombre()); // Se meten los elementos de R.getGabineteXReservas
            }

            reservaDto.setNombreGabinete(gabinetesString);
            reservaDtos.add(reservaDto);
        }

        return reservaDtos;
    }
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
