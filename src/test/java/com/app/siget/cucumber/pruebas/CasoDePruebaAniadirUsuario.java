package com.app.siget.cucumber.pruebas;


import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAniadirUsuario {

	@Given("^En la vista admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password, String confirmacionPassword, String rol) throws Throwable {
		rol = "ADMIN";
		nombre="admin";
		password="Password3";
		Manager.get().register(nombre, email, password, rol);
	}
	@Then("^Se ha creado el usuario correctamente$")
	public void se_ha_creado_el_usuario_correctamente() throws Throwable {
	}


}