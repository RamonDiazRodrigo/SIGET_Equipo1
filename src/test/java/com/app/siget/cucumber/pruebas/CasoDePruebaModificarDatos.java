package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaModificarDatos {
	
	@Given("^En la vista user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void en_la_vista_user(String nombre, String email, String password) throws Throwable {
	   
	}
	
	@When("^El user \"([^\"]*)\" con contraseña \"([^\"]*)\" ha modificado \"([^\"]*)\"$")
	public void el_user_con_contraseña_ha_modificado(String nombre, String contraseña, String emailNuevo) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contraseña);
	}

	@Then("^El user \"([^\"]*)\" ha modificado el correo \"([^\"]*)\" correctamente$")
	public void el_user_ha_modificado_el_correo_correctamente(String nombre, String email) throws Throwable {
	assertEquals(email,UserDAO.findUser(nombre).getEmail());
	//Modificamos de nuevo el usuario para dejarlo como estaba
	Manager.get().modificarUsuario("Daniel","daniel@gmail.com","Daniel1");
	}
	
	
	
	@When("^El user \"(.*?)\" con el correo \"(.*?)\"  ha modificado \"(.*?)\"$")
	public void el_user_con_el_correo_ha_modificado(String nombre, String emailNuevo, String contraseña) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contraseña);
	}

	@Then("^El user \"([^\"]*)\" ha modificado la contraseña \"([^\"]*)\" correctamente$")
	public void el_user_ha_modificado_la_contraseña_correctamente(String nombre, String contraseña) throws Throwable {
	assertEquals(Manager.get().encriptarMD5(contraseña),UserDAO.findUser(nombre).getPassword());
	//Modificamos de nuevo el usuario para dejarlo como estaba
	Manager.get().modificarUsuario("Daniel","daniel@gmail.com","Daniel1");
	}
	
}