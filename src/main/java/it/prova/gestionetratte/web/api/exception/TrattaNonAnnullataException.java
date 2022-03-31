package it.prova.gestionetratte.web.api.exception;

public class TrattaNonAnnullataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrattaNonAnnullataException(String message) {
		super(message);
	}

}
