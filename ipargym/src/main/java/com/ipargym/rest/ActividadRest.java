package com.ipargym.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.ipargym.dtos.ActividadCrearDto;
import com.ipargym.dtos.ActividadDetalleDto;
import com.ipargym.dtos.ActividadListadoDto;
import com.ipargym.servicios.ActividadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/actividades")
@RequiredArgsConstructor
public class ActividadRest {

	private final ActividadService actividadService;

	@GetMapping
	public List<ActividadListadoDto> listarActividades() {

		return actividadService.listarActividades();
	}

	@GetMapping("/{id}")
	public ActividadDetalleDto buscarActividadPorId(@PathVariable("id") Long id) {

		return actividadService.buscarActividadPorId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ActividadDetalleDto crearActividad(@Valid @RequestBody ActividadCrearDto dto) {

		return actividadService.crearActividad(dto);
	}

}
