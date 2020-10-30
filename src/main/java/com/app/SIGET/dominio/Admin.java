package com.app.SIGET.dominio;

import org.json.JSONObject;

public class Admin extends User {

	public Admin(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.rol = Rol.ADMIN;

	}

	@Override
	protected JSONObject toJSON() {
		// Por rellenar
		return null;
	}

	@Override
	protected Horario getHorario() {
		// Por implementar
		return null;
	}

}
