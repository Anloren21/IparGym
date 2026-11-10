package com.ipargym.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipargym.dtos.ActividadDetalleDto;
import com.ipargym.dtos.ActividadListadoDto;
import com.ipargym.entidades.Actividad;

@Mapper(componentModel = "spring")
public interface ActividadMapper {

	@Mapping(target = "plazasLibres", source = "plazasLibres")
	ActividadListadoDto toListadoDto(Actividad actividad, Integer plazasLibres);

	@Mapping(target = "plazasLibres", source = "plazasLibres")
	ActividadDetalleDto toDetalleDto(Actividad actividad, Integer plazasLibres);

}
