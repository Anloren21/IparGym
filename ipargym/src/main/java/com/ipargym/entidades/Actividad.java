
package com.ipargym.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "actividades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	@Column(nullable = false, length = 40)
	private String nombre;

	private String descripcion;

	private String monitor;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime fechaHora;

	private Integer duracionMinutos;

	@NotNull
	@Min(1)
	@Column(nullable = false)
	private Integer plazas;

}
