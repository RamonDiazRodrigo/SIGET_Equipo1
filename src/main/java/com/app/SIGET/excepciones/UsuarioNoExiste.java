package com.app.SIGET.excepciones;

public class UsuarioNoExiste extends Exception {
	public UsuarioNoExiste() {
		// No se necesita hacer nada en el contructor
	}

	@Override
	public String getMessage() {
		return "El usuario no existe, crealo primero";
	}
}
