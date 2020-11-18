package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.CredencialesInvalidasException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaLogin {
	
	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos asistente$")
	public void acceso_con_y_correctos_asistente(String nombre, String password) throws Exception {
		nombre="asistente";
		password="Password1";
		Manager.get().login(nombre, password);
	}

	@When("^los datos son correctos y de un asistente$")
	public void los_datos_son_correctos_y_de_un_asistente() {
	}

	@Then("^Accedo a la pantalla principal de asistente$")
	public void accedo_a_la_pantalla_principal_de_asistente() {
		// La pagina actual es Asistente.html
	}

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos admin$")
	public void acceso_con_y_correctos_admin(String nombre, String password) throws Exception {
		nombre="admin";
		password="Password3";
		Manager.get().login(nombre, password);
	}

	@When("^los datos son correctos y de un administrador$")
	public void los_datos_son_correctos_y_de_un_administrador() {
	}

	@Then("^Accedo a la pantalla principal de admin$")
	public void accedo_a_la_pantalla_principal_de_admin() {
		// La pagina actual es Asistente.html
	}

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\"$")
	public void acceso_con_y(String nombre, String password) throws Exception {
		password="Password2";
		Manager.get().login(nombre, password);
	}

	@When("^intento acceder con el \"([^\"]*)\" correcto y \"([^\"]*)\" mal$")
	public void intento_acceder_con_el_correcto_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion CredencialesInvalidas$")
	public void se_lanza_la_excepcion_CredencialesInvalidas() throws CredencialesInvalidasException {

	}

}