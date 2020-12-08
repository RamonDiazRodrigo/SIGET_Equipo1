package com.app.siget.cucumber.pruebas;


import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAniadirUsuario {

	//Caso 1
	@Given("^En la vista admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password,String passwordConfirm, String confirmacionPassword, String rol) throws Throwable {
		nombre="admin2";
		email = "admin@admin.com";
		password="Password2";
		passwordConfirm = "Password2";
		rol = "ADMIN";
		if (password == passwordConfirm){
			try{
				Manager.get().register(nombre, email, password, rol);
			} catch (Exception e){
				System.out.println("Ha ocurrido un error");
				exception = true;
			}
		}else{
			System.out.println("Las contraseñas no coinciden");
		}
	}
	@When("^los datos son correctos$")
	public void los_datos_son_correctos() {
	}

	@Then("^Se ha creado el usuario correctamente$")
	public void se_ha_creado_el_usuario_correctamente() throws Throwable {
	}

	//Caso 2
	@Given("^Aniado con \"([^\"]*)\" correcta y \"([^\"]*)\" incorrecta$")
	public void acceso_con_correcto_y_incorrecto(String nombre, String email, String password,String passwordConfirm, String confirmacionPassword, String rol){
		exception = false;
		nombre="admin2";
		email = "admin@admin.com";
		rol = "ADMIN";
		password="Password1";
		confirmacionpassword="Password2";
		  try {
				Manager.get().register(nombre, email, password, rol);
			    }catch(Exception e) {
			    	System.out.println("Ha entrado aqui caso 2");
		            exception = true;
		        }
	}

	@When("^intento aniadir con el \"([^\"]*)\" correcta y \"([^\"]*)\" mal$")
	public void intento_aniadir_con_el_correcta_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion ContrasenasDistintas$")
	public void se_lanza_la_excepcion_ContrasenasDistintas() {
		assertEquals(true, exception);
	}

	//Caso 3
	@Given("^Aniado con \"([^\"]*)\" incorrecta y \"([^\"]*)\" incorrecta$")
	public void acceso_con_incorrecta_y_incorrecta(String nombre, String email, String password,String passwordConfirm, String confirmacionPassword, String rol){
		exception = false;
		nombre="admin2";
		rol = "ADMIN";
		email = "emailemail.com";
		password="password";
		Confirmacionpassword="password";
		  try {
				Manager.get().register(nombre, email, password, rol);
			    }catch(Exception e) {
			    	System.out.println("Ha entrado aqui caso 3");
		            exception = true;
		        }
	}

	@When("^intento aniadir con el \"([^\"]*)\" incorrecta y \"([^\"]*)\" incorrecta$")
	public void intento_aniadir_con_el_correcto_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion ContrasenasNoValida$")
	public void se_lanza_la_excepcion_ContrasenasNoValida() {
		assertEquals(true, exception);
	}

	//Caso 4
	@Given("^Aniado con \"([^\"]*)\" incorrecto$")
	public void acceso_con_incorrecto(String nombre, String email, String password,String passwordConfirm, String confirmacionPassword, String rol){
		exception = false;
		nombre="admin2";
		rol = "ADMIN";
		password="Password1";
		confirmacionpassword="Password2";
		email = "emailemail.com";
		  try {
				Manager.get().register(nombre, email, password, rol);
			    }catch(Exception e) {
			    	System.out.println("Ha entrado aqui caso 4");
		            exception = true;
		        }
	}

	@When("^intento aniadir con el \"([^\"]*)\" mal$")
	public void intento_aniadir_con_el_mal(String arg1) {

	}

	@Then("^se lanza la excepcion ContrasenasNoValida$")
	public void se_lanza_la_excepcion_EmailNoValida() {
		assertEquals(true, exception);
	}

	

}