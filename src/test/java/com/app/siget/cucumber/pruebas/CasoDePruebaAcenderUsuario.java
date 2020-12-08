package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaAcenderUsuario {

	@Given("^un usuario \"([^\"]*)\"$")
	public void un_usuario(String arg1) throws Throwable {

	}

	@When("^un administrador asciende al usuario \"([^\"]*)\"$")
	public void un_administrador_asciende_al_usuario(String nombre) throws Throwable {
		Manager.get().ascenderUsuario(nombre);
	}

	@Then("^el usuario \"([^\"]*)\" ahora es administrador$")
	public void el_usuario_ahora_es_administrador(String nombre) throws Throwable {
		
		assertEquals(true, Manager.get().isAdmin(nombre));
	}

	@And("^el usuario \"([^\"]*)\" ya es administrador$")
	public void el_usuario_ya_es_administrador(String nombre) throws Throwable {
		
	}

	@Then("^el usuario \"([^\"]*)\" sigue siendo administrador$")
	public void el_usuario_sigue_siendo_administrador(String nombre) throws Throwable {
		assertEquals(true, Manager.get().isAdmin(nombre));
	}

}
