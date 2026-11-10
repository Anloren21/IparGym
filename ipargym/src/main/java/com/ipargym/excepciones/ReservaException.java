package com.ipargym.excepciones;

public class ReservaException extends RuntimeException {

	private static final long serialVersionUID = -6100415713231035024L;

	public enum Motivo {

		ACTIVIDAD_NO_ENCONTRADA, ACTIVIDAD_CELEBRADA, ACTIVIDAD_COMPLETA, RESERVA_DUPLICADA, RESERVA_NO_ENCONTRADA,
		USUARIO_NO_ENCONTRADO

	}

	private final Motivo motivo;

	public ReservaException(Motivo motivo, String mensaje) {
		super(mensaje);
		this.motivo = motivo;
	}

	public Motivo getMotivo() {
		return motivo;
	}

}
