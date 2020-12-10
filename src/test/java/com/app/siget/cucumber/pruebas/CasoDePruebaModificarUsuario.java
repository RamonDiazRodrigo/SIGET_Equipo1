package com.app.siget.cucumber.pruebas; 

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaModificarUsuario {
	
	@Given("^Como admin en la vista del user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void como_adnmin_en_la_vista_del_user(String nombre, String email, String password) throws Throwable {
	   
	}
	
	@When("^Como admin el user\"(.*?)\"con contrasenia \"(.*?)\" ha sido modificado \"(.*?)\"$")
	public void como_admin_el_user_con_contrasenia_ha_sido_modificado(String nombre, String contraseña, String emailNuevo) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contraseña);
	}

	@Then("^Como admin el user\"(.*?)\"ha sido modificado el correo \"(.*?)\" correctamente$")
	public void como_admin_el_user_ha_sido_modificado_el_correo_correctamente(String nombre, String email) throws Throwable {
	assertEquals(email,UserDAO.findUser(nombre).getEmail());
	//Modificamos de nuevo el usuario para dejarlo como estaba
	Manager.get().modificarUsuario("Alvaro","alvaro@gmail.com","Alvaro1");
	}
	
	
	
	@When("^Como admin el user \"(.*?)\" con el correo \"(.*?)\"  ha sido modificado \"(.*?)\"$")
	public void como_admin_el_user_con_el_correo_ha_sido_modificado(String nombre, String emailNuevo, String contraseña) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contraseña);
	}

	@Then("^Como admin el user \"([^\"]*)\" ha sido modificado la contrasenia \"([^\"]*)\" correctamente$")
	public void como_admin_el_user_ha_sido_modificado_la_contrasenia_correctamente(String nombre, String contraseña) throws Throwable {
	assertEquals(Manager.get().encriptarMD5(contraseña),UserDAO.findUser(nombre).getPassword());
	//Modificamos de nuevo el usuario para dejarlo como estaba
	Manager.get().modificarUsuario("Alvaro","alvaro@gmail.com","Alvaro1");
	}
	
}