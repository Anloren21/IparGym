
package com.ipargym.dtos;

import java.time.LocalDateTime;

public record ReservaDto(Long id, Long idActividad, String nombreActividad, LocalDateTime fechaHora,
		LocalDateTime fechaReserva) {
	
}
