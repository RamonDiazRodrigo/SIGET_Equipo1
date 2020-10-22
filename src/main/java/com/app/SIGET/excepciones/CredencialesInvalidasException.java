package com.app.SIGET.excepciones;

/**
 * @author Equipo1
 *
 */
public class CredencialesInvalidasException extends Exception {

	public CredencialesInvalidasException() {
		// No se necesita hacer nada en el contructor
	}
	/**
	 * @return "Las contrase�as no coinciden"
	 */
	@Override
	public String getMessage() {
		return "Credenciales Invalidas";
	}

}
