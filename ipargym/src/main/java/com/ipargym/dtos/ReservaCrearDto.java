package com.ipargym.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservaCrearDto(@NotNull @Positive Long idActividad) {
	
}
