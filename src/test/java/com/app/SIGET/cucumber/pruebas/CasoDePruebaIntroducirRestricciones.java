package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;
import com.app.SIGET.excepciones.UsuarioNoExiste;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaIntroducirRestricciones {	

	
	@Given("^\"([^\"]*)\", \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\",  \"([^\"]*)\" y \"([^\"]*)\" involucrado$")
	public void String_String_String_String_y_involucrado(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario) throws NumberFormatException {
		dia="LUNES";
		horaI="10";
		minutosI="00";
		horaF="11";
		minutosF="30";
		usuario="nombre";
		Manager.get().insertarActividad(nombre, dia, horaI, minutosI, horaF, minutosF, usuario);
	}

	@Then("^se aniade la actividad no laborable con \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" y se vincula al \"([^\"]*)\"$")
	public void se_aniade_la_actividad_no_laborable_con_y_y_se_vincula_al(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario) throws NumberFormatException {
		dia="LUNES";
		horaI="10";
		minutosI="00";
		horaF="11";
		minutosF="30";
		usuario="nombre";
	}

	@When("^usuario no esta registrado$")
	public void usuario_no_esta_registrado()  {

	}

	@Then("^se lanza excepcion UsuarioNoExiste$")
	public void se_lanza_excepcion_UsuarioNoExiste() throws UsuarioNoExiste {
	}

}
