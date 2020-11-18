package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaModificarDatos {
	@Given("^En la vista user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_user_y(String nombre, String email, String password, String rol) throws Throwable {
	    password="Password2";
	    nombre="admin";
	    rol="ADMIN";
		Manager.get().modificarUsuario(nombre,email,password);
	    Manager.get().login(nombre, password);
	   
	}

	@Then("^Se han modificado los datos correctamente$")
	public void se_han_modificado_los_datos_correctamente() throws Throwable {
	    
	}
	
}