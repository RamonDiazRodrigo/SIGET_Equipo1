package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaModificarDatos {
	@Given("^En la vista user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_user_y(String nombre, String email, String password, String rol) throws Throwable {
	    Manager.get().modificarUsuario(nombre,email,password);
	    Manager.get().login(nombre, password);
	   
	}

	@Then("^Se han modificado los datos correctamente$")
	public void se_han_modificado_los_datos_correctamente() throws Throwable {
	    
	}
	
}