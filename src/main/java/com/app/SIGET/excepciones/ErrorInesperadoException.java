package com.app.SIGET.excepciones;

/**
 * @author Equipo1
 *
 */
public class ErrorInesperadoException extends Exception {

	public ErrorInesperadoException(Exception e) {
		// No se necesita hacer nada en el contructor
	}

	/**
	 * @return "Las contrase�as no coinciden"
	 */
	@Override
	public String getMessage() {
		return "Error inesperado";
	}

}
