package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaVistaGeneral {

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" admin$")
	public void acceso_con_y_admin(String nombre, String password) throws Throwable {
		Manager.get().login(nombre, password);
	}

	@When("^en la vista admin\\.html se clicka el boton verAgendaGeneral$")
	public void en_la_vista_admin_html_se_clicka_el_boton_verAgendaGeneral() throws Throwable {
		Manager.get().leerReuniones();
	}

	@Then("^veo la agenda general$")
	public void veo_la_agenda_general() throws Throwable {
	}

}