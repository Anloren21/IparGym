package com.ipargym.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.ipargym.dtos.ReservaCrearDto;
import com.ipargym.dtos.ReservaDto;
import com.ipargym.servicios.ReservaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/reservas")
@RequiredArgsConstructor
public class ReservaRest {

	private final ReservaService reservaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReservaDto reservar(@Valid @RequestBody ReservaCrearDto dto, @AuthenticationPrincipal Jwt jwt) {

		return reservaService.reservar(dto, jwt.getSubject());
	}

	@GetMapping
	public List<ReservaDto> misReservas(@AuthenticationPrincipal Jwt jwt) {

		return reservaService.misReservas(jwt.getSubject());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt jwt) {

		reservaService.cancelar(id, jwt.getSubject());
	}

}
