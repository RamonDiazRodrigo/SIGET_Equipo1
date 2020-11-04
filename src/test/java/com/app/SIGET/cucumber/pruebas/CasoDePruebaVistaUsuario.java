package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaVistaUsuario {
	
	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" asistente$")
	public void acceso_con_y_asistente(String nombre, String password) throws Throwable {
		Manager.get().login(nombre, password);
	}
	
	@Then("^veo la agenda del \"([^\"]*)\"$")
	public void veo_la_agenda_del(String usuario) throws Throwable {
		Manager.get().leerActividades(usuario);
	}

}