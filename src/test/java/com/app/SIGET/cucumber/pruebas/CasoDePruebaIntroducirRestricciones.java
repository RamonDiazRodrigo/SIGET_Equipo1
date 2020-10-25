package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class CasoDePruebaIntroducirRestricciones {

	@Given("^\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" involucrado$")
	public void y_involucrado(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^los datos son correctos$")
	public void los_datos_son_correctos() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^se añade la actividad no laborable con \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" y se vincula al \"([^\"]*)\"$")
	public void se_añade_la_actividad_no_laborable_con_y_y_se_vincula_al(String arg1, String arg2, String arg3,
			String arg4, String arg5, String arg6, String arg7) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^se lanza excepcion UsuarioNoExiste$")
	public void se_lanza_excepcion_UsuarioNoExiste() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
