package com.app.SIGET.cucumber.pruebas;

import com.app.SIGET.dominio.Manager;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePrueba {
	
	@Given("acceso con \"nombre\" y \"password\"")
	public void acceder(String arg1, String arg2) throws Throwable {
		Manager.login(arg1,arg2);
	}
	@Then("puedo ver mis reuniones")
	public void verReuniones() throws Throwable {
	}
	

	@When("^Valido la operacion \"([^\"]*)\" vacio y \"([^\"]*)\" bien$")
	public void valido_la_operacion_vacio_y_bien(String arg1, String arg2) throws Throwable {
		Manager.login("",arg2);
	}
	
	@When("^Valido la operacion \"([^\"]*)\" bien y \"([^\"]*)\" incorrecto$")
	public void valido_la_operacion_bien_y_incorrecto(String arg1, String arg2) throws Throwable {
		Manager.login(arg1,"");
	}
	
	@Then("^Da un error de acceso$")
	public void da_un_error() throws Throwable {
		Manager.error();
	}

	@Given("Registro con \"nombre\" y \"password\"")
	public void valido_la_operacion_y(String arg1, String arg2) throws Throwable {
	    Manager.register(arg1,arg2);
	}

	@Then("crea un usuario")
	public void crear() throws Throwable {
	}
	
	@When("el \"nombre\" esta vacio y \"password\" bien")
	public void registoNombreVacio(String arg1, String arg2) throws Throwable {
		Manager.login(null,arg2);
	}
	
	@When("el \"nombre\" esta bien y \"password\" vacio")
	public void registoPasswordVacio(String arg1, String arg2) throws Throwable {
		Manager.login(arg1,null);
	}
	@Then("^Da un error de registro")
	public void da_un_error_registro() throws Throwable {
		Manager.error();
	}
}