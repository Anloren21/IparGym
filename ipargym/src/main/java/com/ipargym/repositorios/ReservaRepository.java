package com.ipargym.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipargym.entidades.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
