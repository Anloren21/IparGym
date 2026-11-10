package com.ipargym.configuracion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ipargym.entidades.Actividad;
import com.ipargym.entidades.Reserva;
import com.ipargym.entidades.Rol;
import com.ipargym.entidades.Usuario;
import com.ipargym.repositorios.ActividadRepository;
import com.ipargym.repositorios.ReservaRepository;
import com.ipargym.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatosIniciales implements CommandLineRunner {

	private final ActividadRepository actividadRepository;
	private final UsuarioRepository usuarioRepository;
	private final ReservaRepository reservaRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) {

		// Evitar duplicar o sobrescribir datos ya creados
		if (actividadRepository.count() > 0 || usuarioRepository.count() > 0 || reservaRepository.count() > 0) {
			return;
		}

		//Usuarios

		Usuario administrador = new Usuario(null, "Administrador", "admin@ipargym.es",
				passwordEncoder.encode("Admin123!"), Rol.ADMINISTRADOR);

		Usuario ana = new Usuario(null, "Ana", "ana@ipargym.es", passwordEncoder.encode("Ana123!"), Rol.SOCIO);

		Usuario carlos = new Usuario(null, "Carlos", "carlos@ipargym.es", passwordEncoder.encode("Carlos123!"),
				Rol.SOCIO);

		usuarioRepository.saveAll(List.of(administrador, ana, carlos));

		//Actividades
		LocalDate hoy = LocalDate.now();

		Actividad spinning = new Actividad(null, "Spinning", "Clase de ciclismo indoor", "Laura",
				hoy.plusDays(1).atTime(10, 0), 45, 12);

		Actividad yoga = new Actividad(null, "Yoga", "Sesión de relajación y flexibilidad", "María",
				hoy.plusDays(1).atTime(17, 0), 60, 1);

		Actividad pilates = new Actividad(null, "Pilates", "Ejercicios de control y fortalecimiento", "Lucía",
				hoy.plusDays(2).atTime(11, 30), 50, 2);

		Actividad crossfit = new Actividad(null, "CrossFit", "Entrenamiento funcional de alta intensidad", "David",
				hoy.plusDays(3).atTime(18, 0), 60, 15);

		Actividad zumba = new Actividad(null, "Zumba", "Clase de baile y ejercicio cardiovascular", "Sofía",
				hoy.plusDays(4).atTime(19, 0), 45, 20);

		Actividad bodyPump = new Actividad(null, "Body Pump", "Entrenamiento de fuerza con pesas", "Miguel",
				hoy.minusDays(1).atTime(10, 0), 60, 10);

		actividadRepository.saveAll(List.of(spinning, yoga, pilates, crossfit, zumba, bodyPump));

		//Reservas

		Reserva reservaYoga = new Reserva(null, ana, yoga, LocalDateTime.now());

		Reserva reservaPilates = new Reserva(null, carlos, pilates, LocalDateTime.now());

		reservaRepository.saveAll(List.of(reservaYoga, reservaPilates));

		System.out.println("Datos iniciales de IparGym cargados correctamente");
	}

}
