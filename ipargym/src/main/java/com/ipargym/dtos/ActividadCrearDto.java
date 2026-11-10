package com.ipargym.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public record ActividadCrearDto(

		@NotBlank @Size(max = 40) String nombre,

		String descripcion,

		String monitor,

		@NotNull @Future LocalDateTime fechaHora,

		@NotNull @Min(1) Integer duracionMinutos,

		@NotNull @Min(1) Integer plazas

) {
}
