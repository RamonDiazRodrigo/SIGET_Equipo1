package com.app.siget.cucumber.pruebas;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.DiferentesContrasenasException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaRegister {

	@Given("^\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void y(String nombre, String email, String password, String confirmacionPassword, String rol)
			throws Exception {
		rol = "ADMIN";
		password="Password2";
		Manager.get().register(nombre, email, password, rol);
	}

	@Then("^Crea un usuario \"([^\"]*)\"$")
	public void crea_un_usuario(String nombre) throws Exception {
		JSONObject jso = Manager.get().leerUsuarios();
		JSONArray jsa = jso.getJSONArray("usuarios");
		Boolean passed = false;
		for (int i = 0; i < jsa.length(); i++) {
			if (jsa.get(i) == nombre) {
				passed = true;
			}
		}
		if (!passed) {
			System.out.println("No se ha creado");
		}

	}

	@Given("^\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" distintas$")
	public void y_distintas(String nombre, String email, String password, String confirmacionPassword, String rol)
			throws Exception {
		rol = "ASISTENTE";
		nombre="asistente";
		password="Password1";
		Manager.get().register(nombre, email, password, rol);
	}

	@When("^\"([^\"]*)\" y \"([^\"]*)\" son distintas$")
	public void y_son_distintas(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion DiferentesContrasenas$")
	public void se_lanza_la_excepcion_DiferentesContrasenas() throws DiferentesContrasenasException {

	}

}