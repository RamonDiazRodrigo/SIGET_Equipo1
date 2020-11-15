package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaEliminarUsuario {
	
	@Given("^En la vista admin ver usuarios seleccionar uno por \"([^\"]*)\" y eliminarlo$")
	public void en_la_vista_admin_ver_usuarios_seleccionar_uno_por_y_eliminarlo(String nombre) throws Throwable {
		
		Manager.get().eliminarUsuario(nombre);
		
	}

	@Then("^Se eliminar el usuario con nombre \"([^\"]*)\"$")
	public void se_eliminar_el_usuario_con_nombre(String nombre) throws Throwable {
	}

}