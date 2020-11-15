package com.app.siget.cucumber.pruebas; 

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaModificarUsuario {
	@Given("^En la vista admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password, String rol) throws Throwable {
	    Manager.get().modificarUsuario(nombre,email,password);
	   
	}

	@When("^\"([^\"]*)\", \"([^\"]*)\" son distintas$")
	public void son_distintas(String arg1, String arg2) throws Throwable {
	    
	}

	@Then("^Se ha modificado el usuario correctamente$")
	public void se_ha_modificado_el_usuario_correctamente() throws Throwable {
	    
	}
	
}