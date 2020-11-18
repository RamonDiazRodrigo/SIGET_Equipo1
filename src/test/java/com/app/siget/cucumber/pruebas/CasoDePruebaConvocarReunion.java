package com.app.siget.cucumber.pruebas;

import org.json.JSONArray;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaConvocarReunion {

	@Given("^\"([^\"]*)\", \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\" y \"([^\"]*)\" involucrados$")
	public void y_involucrados(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuarios) throws NumberFormatException {
		nombre="asistente";
		dia="LUNES";
		horaI="15";
		minutosI="00";
		horaF="16";
		minutosF="30";
		usuarios= new JSONArray().toString();
		Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuarios, "true");
	}

	@Then("^Crea una reunion \"([^\"]*)\" y la aniade a reuniones pendientes si no tienen ese horario ocupado$")
	public void crea_una_reunion_y_la_aniade_a_reuniones_pendientes_si_no_tienen_ese_horario_ocupado(String arg1) throws Throwable {
	}

}