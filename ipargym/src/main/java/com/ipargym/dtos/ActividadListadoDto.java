package com.ipargym.dtos;

import java.time.LocalDateTime;

public record ActividadListadoDto(Long id, String nombre, LocalDateTime fechaHora, Integer duracionMinutos,
		Integer plazasLibres) {
	
}
