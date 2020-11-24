package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.FranjaHorariaOcupadaException;
import com.app.siget.excepciones.UsuarioNoExisteException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaIntroducirRestricciones {	

	@Given("^\"([^\"]*)\", \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\", \"([^\"]*)\" involucrado y si es \"([^\"]*)\"$")
	public void involucrado_y_si_es(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario, String reunion) throws NumberFormatException, Exception {
		nombre="asistente";
		dia="LUNES";
		horaI="10";
		minutosI="00";
		horaF="11";
		minutosF="30";
		usuario="nombre";
		reunion="true";
		Manager.get().insertarActividad(nombre, dia, horaI, minutosI, horaF, minutosF, usuario, reunion);
	}

	@Then("^se aniade la actividad no laborable con \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y se vincula al \"([^\"]*)\"$")
	public void se_aniade_la_actividad_no_laborable_con_y_se_vincula_al(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario, String reunion) throws NumberFormatException {
		nombre="asistente";
		dia="LUNES";
		horaI="10";
		minutosI="00";
		horaF="11";
		minutosF="30";
		usuario="nombre";
		reunion="true";
	}

	@When("^usuario no esta registrado$")
	public void usuario_no_esta_registrado()  {

	}

	@Then("^se lanza excepcion UsuarioNoExiste$")
	public void se_lanza_excepcion_UsuarioNoExiste() throws UsuarioNoExisteException {
	}

}
