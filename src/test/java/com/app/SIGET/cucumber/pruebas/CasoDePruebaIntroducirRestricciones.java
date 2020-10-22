package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class CasoDePruebaIntroducirRestricciones {
	@Given("^\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" involucrado$")
	public void y_involucrado(String arg1, String arg2, String arg3) throws Throwable {
		//Manager.get().anadirRestriccion(arg1, arg2, arg3);
	}

	@Then("^se añade la restriccion horaria$")
	public void se_añade_la_restriccion_horaria() throws Throwable {
		System.out.println("Restricción añadida");
	}

	@When("^usuario no esta registrado$")
	public void usuario_no_esta_registrado() throws Throwable {
		System.out.println("Usuario no registrado");
	}

	@Then("^se lanza excepcion usuario no existe$")
	public void se_lanza_excepcion_usuario_no_existe() throws Throwable {
		System.out.println("usuarioNoExisteException");

	}

}
