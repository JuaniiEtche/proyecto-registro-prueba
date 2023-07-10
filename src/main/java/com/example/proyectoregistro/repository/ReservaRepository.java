package com.example.proyectoregistro.repository;

import com.example.proyectoregistro.entities.Reserva;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findReservasByEstado(String estado);

}
