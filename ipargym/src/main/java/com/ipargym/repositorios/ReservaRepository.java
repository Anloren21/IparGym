package com.ipargym.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipargym.entidades.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	long countByActividadId(Long idActividad);

	boolean existsByUsuarioIdAndActividadId(Long idUsuario, Long idActividad);

	List<Reserva> findByUsuarioEmailOrderByActividadFechaHoraAsc(String email);

	Optional<Reserva> findByIdAndUsuarioEmail(Long idReserva, String email);
}
