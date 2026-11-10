package com.ipargym.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipargym.dtos.ReservaDto;
import com.ipargym.entidades.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

	@Mapping(target = "idActividad", source = "actividad.id")
	@Mapping(target = "nombreActividad", source = "actividad.nombre")
	@Mapping(target = "fechaHora", source = "actividad.fechaHora")
	ReservaDto toDto(Reserva reserva);

}
