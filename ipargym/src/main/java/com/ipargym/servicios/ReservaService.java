package com.ipargym.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipargym.dtos.ReservaCrearDto;
import com.ipargym.dtos.ReservaDto;
import com.ipargym.entidades.Actividad;
import com.ipargym.entidades.Reserva;
import com.ipargym.entidades.Usuario;
import com.ipargym.excepciones.ReservaException;
import com.ipargym.excepciones.ReservaException.Motivo;
import com.ipargym.mappers.ReservaMapper;
import com.ipargym.repositorios.ActividadRepository;
import com.ipargym.repositorios.ReservaRepository;
import com.ipargym.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaService {

	private final ReservaRepository reservaRepository;
	private final ActividadRepository actividadRepository;
	private final UsuarioRepository usuarioRepository;
	private final ReservaMapper reservaMapper;

	public ReservaDto reservar(ReservaCrearDto dto, String emailUsuario) {

		return reservar(dto.idActividad(), emailUsuario);
	}

	public ReservaDto reservar(Long idActividad, String emailUsuario) {

		Actividad actividad = actividadRepository.buscarPorIdConBloqueo(idActividad)
				.orElseThrow(() -> new ReservaException(Motivo.ACTIVIDAD_NO_ENCONTRADA, "La actividad no existe"));

		LocalDateTime ahora = LocalDateTime.now();

		if (!actividad.getFechaHora().isAfter(ahora)) {
			throw new ReservaException(Motivo.ACTIVIDAD_CELEBRADA, "La actividad ya se ha celebrado");
		}

		Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
				.orElseThrow(() -> new ReservaException(Motivo.USUARIO_NO_ENCONTRADO, "El usuario no existe"));

		if (reservaRepository.existsByUsuarioIdAndActividadId(usuario.getId(), actividad.getId())) {

			throw new ReservaException(Motivo.RESERVA_DUPLICADA, "Ya tienes una reserva para esta actividad");
		}

		long ocupadas = reservaRepository.countByActividadId(actividad.getId());

		if (ocupadas >= actividad.getPlazas()) {
			throw new ReservaException(Motivo.ACTIVIDAD_COMPLETA, "No quedan plazas disponibles");
		}

		Reserva reserva = new Reserva(null, usuario, actividad, ahora);

		return reservaMapper.toDto(reservaRepository.save(reserva));
	}

	@Transactional(readOnly = true)
	public List<ReservaDto> misReservas(String emailUsuario) {

		return reservaRepository.findByUsuarioEmailOrderByActividadFechaHoraAsc(emailUsuario).stream()
				.map(reservaMapper::toDto).toList();
	}

	public void cancelar(Long idReserva, String emailUsuario) {

		Reserva reserva = reservaRepository.findByIdAndUsuarioEmail(idReserva, emailUsuario)
				.orElseThrow(() -> new ReservaException(Motivo.RESERVA_NO_ENCONTRADA,
						"La reserva no existe o no pertenece al usuario"));

		reservaRepository.delete(reserva);
	}

}
