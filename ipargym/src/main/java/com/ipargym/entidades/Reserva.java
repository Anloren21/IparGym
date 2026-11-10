
package com.ipargym.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "reservas",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_reserva_usuario_actividad",
            columnNames = {"usuario_id", "actividad_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaReserva;

}

