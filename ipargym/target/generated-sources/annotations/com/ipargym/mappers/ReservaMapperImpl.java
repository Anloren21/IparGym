package com.ipargym.mappers;

import com.ipargym.dtos.ReservaDto;
import com.ipargym.entidades.Actividad;
import com.ipargym.entidades.Reserva;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-10T23:52:09+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4.1 (Eclipse Adoptium)"
)
@Component
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public ReservaDto toDto(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        Long idActividad = null;
        String nombreActividad = null;
        LocalDateTime fechaHora = null;
        Long id = null;
        LocalDateTime fechaReserva = null;

        idActividad = reservaActividadId( reserva );
        nombreActividad = reservaActividadNombre( reserva );
        fechaHora = reservaActividadFechaHora( reserva );
        id = reserva.getId();
        fechaReserva = reserva.getFechaReserva();

        ReservaDto reservaDto = new ReservaDto( id, idActividad, nombreActividad, fechaHora, fechaReserva );

        return reservaDto;
    }

    private Long reservaActividadId(Reserva reserva) {
        Actividad actividad = reserva.getActividad();
        if ( actividad == null ) {
            return null;
        }
        return actividad.getId();
    }

    private String reservaActividadNombre(Reserva reserva) {
        Actividad actividad = reserva.getActividad();
        if ( actividad == null ) {
            return null;
        }
        return actividad.getNombre();
    }

    private LocalDateTime reservaActividadFechaHora(Reserva reserva) {
        Actividad actividad = reserva.getActividad();
        if ( actividad == null ) {
            return null;
        }
        return actividad.getFechaHora();
    }
}
