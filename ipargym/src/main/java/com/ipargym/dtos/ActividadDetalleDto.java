
package com.ipargym.dtos;

import java.time.LocalDateTime;

public record ActividadDetalleDto(Long id, String nombre, String descripcion, String monitor, LocalDateTime fechaHora,
		Integer duracionMinutos, Integer plazas, Integer plazasLibres) {
}
