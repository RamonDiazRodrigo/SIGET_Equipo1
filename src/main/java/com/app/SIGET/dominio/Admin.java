package com.app.SIGET.dominio;

public class Admin extends User {

	public Admin(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.rol = Rol.ADMIN;
		
	}

}
