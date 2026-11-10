package com.ipargym.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipargym.dtos.ActividadDetalleDto;
import com.ipargym.dtos.ActividadListadoDto;
import com.ipargym.entidades.Actividad;
import com.ipargym.excepciones.ReservaException;
import com.ipargym.excepciones.ReservaException.Motivo;
import com.ipargym.mappers.ActividadMapper;
import com.ipargym.repositorios.ActividadRepository;
import com.ipargym.repositorios.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActividadService {

	private final ActividadRepository actividadRepository;
	private final ReservaRepository reservaRepository;
	private final ActividadMapper actividadMapper;

	public List<ActividadListadoDto> listarActividades() {

		return actividadRepository.findByFechaHoraAfterOrderByFechaHoraAsc(LocalDateTime.now()).stream()
				.map(actividad -> actividadMapper.toListadoDto(actividad, calcularPlazasLibres(actividad))).toList();
	}

	public ActividadDetalleDto buscarActividadPorId(Long idActividad) {

		Actividad actividad = actividadRepository.findById(idActividad)
				.orElseThrow(() -> new ReservaException(Motivo.ACTIVIDAD_NO_ENCONTRADA, "La actividad no existe"));

		return actividadMapper.toDetalleDto(actividad, calcularPlazasLibres(actividad));
	}

	private Integer calcularPlazasLibres(Actividad actividad) {

		long plazasOcupadas = reservaRepository.countByActividadId(actividad.getId());

		return Math.toIntExact(actividad.getPlazas() - plazasOcupadas);
	}

}
