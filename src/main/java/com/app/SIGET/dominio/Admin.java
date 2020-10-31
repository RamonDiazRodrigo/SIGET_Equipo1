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
		JSONObject jso = new JSONObject();
		jso.put("name", this.getName());
		jso.put("email", this.getEmail());
		jso.put("password", this.getPassword());
		jso.put("rol", this.getRol());
		return jso;
	}


}
