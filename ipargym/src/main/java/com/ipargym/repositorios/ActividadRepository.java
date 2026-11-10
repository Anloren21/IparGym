package com.ipargym.repositorios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ipargym.entidades.Actividad;

import jakarta.persistence.LockModeType;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

	List<Actividad> findByFechaHoraAfterOrderByFechaHoraAsc(LocalDateTime fechaHora);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT a FROM Actividad a WHERE a.id = :id")
	Optional<Actividad> buscarPorIdConBloqueo(@Param("id") Long id);

}
