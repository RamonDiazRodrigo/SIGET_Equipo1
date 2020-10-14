package com.app.SIGET.cucumber.pruebas;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePrueba {
	
	@Given("^Pongo mis credenciales$")
	public void pongo_mis_credenciales() throws Throwable {

	}

	@When("^intento acceder con el \"([^\"]*)\" correcto y \"([^\"]*)\" mal$")
	public void intento_acceder_con_el_correcto_y_mal(String arg1, String arg2) throws Throwable {
	    UsuarioDAO.login(arg1,arg2);
	}

	@Then("^da error$")
	public void da_error() throws Throwable {
	 UsuarioDAO.error();
	}

	@Given("^Escribo las credenciales que quiero$")
	public void escribo_las_credenciales_que_quiero() throws Throwable {

	}

	@When("^Valido la operacion \"([^\"]*)\" y \"([^\"]*)\"$")
	public void valido_la_operacion_y(String arg1, String arg2) throws Throwable {
	    UsuarioDAO.register(arg1,arg2);
	}

	@Then("^Crea un usuario$")
	public void crea_un_usuario() throws Throwable {
		
	}

	@When("^Valido la operacion \"([^\"]*)\" vacio y \"([^\"]*)\" bien$")
	public void valido_la_operacion_vacio_y_bien(String arg1, String arg2) throws Throwable {
		UsuarioDAO.register("",arg2);
	}

	@Then("^Da un error$")
	public void da_un_error() throws Throwable {
		UsuarioDAO.error();
	}

	@When("^Valido la operacion \"([^\"]*)\" bien y \"([^\"]*)\" incorrecto$")
	public void valido_la_operacion_bien_y_incorrecto(String arg1, String arg2) throws Throwable {
		UsuarioDAO.register(arg1,"");
	}

	
}