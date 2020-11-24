package com.app.siget.cucumber.pruebas;

import org.json.JSONArray;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAsistirReuniones {

	@Given("^\"([^\"]*)\", \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\" y un \"([^\"]*)\"$")
	public void y_un(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF,
			String usuario) throws Throwable {
		nombre="asistente";
		dia = "LUNES";
		horaI = "15";
		minutosI = "00";
		horaF = "16";
		minutosF = "30";
		usuario = new JSONArray().toString();
	}

	@Then("^el \"([^\"]*)\" acepta la reunion \"([^\"]*)\" y aparece en su agenda$")
	public void el_acepta_la_reunion_y_aparece_en_su_agenda(String usuario, String id) throws Throwable {
		id="1";
		usuario="asistente";
		Manager.get().aceptarReunion(usuario, Integer.parseInt(id));
	}

	@Then("^el \"([^\"]*)\" acepta la reunion \"([^\"]*)\" y no aparece en su agenda$")
	public void el_acepta_la_reunion_y_no_aparece_en_su_agenda(String usuario, String id) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		id="1";
		usuario="asistente";
		Manager.get().rechazarReunion(usuario, Integer.parseInt(id));
	}

}