package com.app.SIGET.cucumber.pruebas;

import org.json.JSONArray;
import org.json.JSONObject;
import com.app.SIGET.dominio.Manager;
import com.app.SIGET.excepciones.DiferentesContrasenasException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaAniadirUsuario {

	@Given("^En la vista admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password, String confirmacionPassword, String rol) throws Throwable {
		rol = "ADMIN";
		Manager.get().register(nombre, email, password, rol);
	}
	@Then("^Se ha creado el usuario correctamente$")
	public void se_ha_creado_el_usuario_correctamente() throws Throwable {
	}


}