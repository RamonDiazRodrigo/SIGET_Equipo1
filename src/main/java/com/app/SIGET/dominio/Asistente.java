package com.app.SIGET.dominio;

public class Asistente extends User {
	
	private Horario horario;

	public Asistente(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.rol = Rol.ADMIN;
		this.horario = new Horario();
	}
	
	public void insertarActividad(Actividad actividad) {

		if (!this.horario.estaOcupado(actividad)) {
			this.horario.insertarActividad(actividad);
		} else {
			System.out.println("mandar error porque ya esta ocupado");
		}

	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	

}
