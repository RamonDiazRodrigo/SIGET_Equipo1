package com.app.siget.cucumber.pruebas;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.DiferentesContrasenasException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaRegister {
	
	//Caso 1
	@Given("^los datos\"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\", \"(.*?)\"$")
	public void los_datos(String nombre, String email, String password,
			String passwordConfirm, String rol) throws Throwable {
		try {
			Manager.get().register(nombre, email, password, rol);
		}catch (Exception e) {
			System.out.println("Ha entrado en la excepcion");
		}
	}
	
	@Then("^el usuario se ha creado correctamente \"(.*?)\"$")
	public void el_usuario_se_ha_creado_correctamente(String usuario) throws Throwable {
		Manager.get().eliminarUsuario(usuario);
	}
	
	//Caso 2
	@Given("^\"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\" y \"(.*?)\" distintas$")
	public void y_distintas(String nombre, String email, String password, String passwordConfirm, String rol) throws Throwable {
		try {
			Manager.get().register(nombre, email, password, rol);
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error");
		}
	}
	
	@When("^\"(.*?)\" y \"(.*?)\" son distintas$")
	public void y_son_distintas(String arg1, String arg2) throws Throwable {}
	
	@Then("^se lanza la excepcion DiferentesContrasenas \"(.*?)\"$")
	public void se_lanza_la_excepcion_DiferentesContrasenas(String usuario){
		Manager.get().eliminarUsuario(usuario);
	}
	
	//Caso 3
	@Given("^el usuario \"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\", \"(.*?)\"$")
	public void el_usuario(String nombre, String email, String password,
			String passwordConfirm, String rol) {
		
		Manager.get().register(nombre, email, password, rol);
		
	}
	
	@When("^ya existe \"(.*?)\"$")
	public void ya_existe(String arg1) throws Throwable {}
	
	@Then("^se lanza la excepcion ya existe \"(.*?)\"$")
	public void se_lanza_la_excepcion_ya_existe(String usuario){
		Manager.get().eliminarUsuario(usuario);
	}

}