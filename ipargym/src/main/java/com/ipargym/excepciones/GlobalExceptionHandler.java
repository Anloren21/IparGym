package com.ipargym.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ReservaException.class)
	public ProblemDetail gestionarReservaException(ReservaException ex) {

		HttpStatus estado = switch (ex.getMotivo()) {

		case ACTIVIDAD_NO_ENCONTRADA, RESERVA_NO_ENCONTRADA -> HttpStatus.NOT_FOUND;

		case ACTIVIDAD_CELEBRADA, ACTIVIDAD_COMPLETA, RESERVA_DUPLICADA -> HttpStatus.CONFLICT;

		case USUARIO_NO_ENCONTRADO -> HttpStatus.UNAUTHORIZED;
		};

		ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, ex.getMessage());

		problema.setProperty("motivo", ex.getMotivo().name());

		return problema;
	}

	@ExceptionHandler(AuthenticationException.class)
	public ProblemDetail gestionarAutenticacion(AuthenticationException ex) {

		return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Email o contraseña incorrectos");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail gestionarValidacion(MethodArgumentNotValidException ex) {

		String mensaje = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).findFirst()
				.orElse("Datos no válidos");

		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, mensaje);
	}

}
