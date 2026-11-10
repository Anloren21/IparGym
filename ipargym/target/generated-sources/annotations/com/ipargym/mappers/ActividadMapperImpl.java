package com.ipargym.mappers;

import com.ipargym.dtos.ActividadDetalleDto;
import com.ipargym.dtos.ActividadListadoDto;
import com.ipargym.entidades.Actividad;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-10T23:52:09+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4.1 (Eclipse Adoptium)"
)
@Component
public class ActividadMapperImpl implements ActividadMapper {

    @Override
    public ActividadListadoDto toListadoDto(Actividad actividad, Integer plazasLibres) {
        if ( actividad == null && plazasLibres == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        LocalDateTime fechaHora = null;
        Integer duracionMinutos = null;
        if ( actividad != null ) {
            id = actividad.getId();
            nombre = actividad.getNombre();
            fechaHora = actividad.getFechaHora();
            duracionMinutos = actividad.getDuracionMinutos();
        }
        Integer plazasLibres1 = null;
        plazasLibres1 = plazasLibres;

        ActividadListadoDto actividadListadoDto = new ActividadListadoDto( id, nombre, fechaHora, duracionMinutos, plazasLibres1 );

        return actividadListadoDto;
    }

    @Override
    public ActividadDetalleDto toDetalleDto(Actividad actividad, Integer plazasLibres) {
        if ( actividad == null && plazasLibres == null ) {
            return null;
        }

        Long id = null;
        String nombre = null;
        String descripcion = null;
        String monitor = null;
        LocalDateTime fechaHora = null;
        Integer duracionMinutos = null;
        Integer plazas = null;
        if ( actividad != null ) {
            id = actividad.getId();
            nombre = actividad.getNombre();
            descripcion = actividad.getDescripcion();
            monitor = actividad.getMonitor();
            fechaHora = actividad.getFechaHora();
            duracionMinutos = actividad.getDuracionMinutos();
            plazas = actividad.getPlazas();
        }
        Integer plazasLibres1 = null;
        plazasLibres1 = plazasLibres;

        ActividadDetalleDto actividadDetalleDto = new ActividadDetalleDto( id, nombre, descripcion, monitor, fechaHora, duracionMinutos, plazas, plazasLibres1 );

        return actividadDetalleDto;
    }
}
