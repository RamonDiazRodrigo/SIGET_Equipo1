package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaAniadirUsuario {

	// Caso 1
	@Given("^En la vista admin \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password,
			String passwordConfirm, String rol) throws Throwable {
		try {
			Manager.get().register(nombre, email, password, rol);
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error");
		}
	}

	@Then("^el usuario se ha creado el usuario correctamente \"(.*?)\"$")
	public void el_usuario_se_ha_creado_el_usuario_correctamente(String nombre) throws Throwable {
		assertEquals(nombre,UserDAO.findUser(nombre).getName());
		Manager.get().eliminarUsuario(nombre);
	}

	// Caso 2
	@Given("^\"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\" y \"(.*?)\" rol no valido$")
	public void y_rol_no_valido(String nombre, String email, String password, 
			String confirmacionpassword, String rol) throws Throwable {
		try {
			Manager.get().register(nombre, email, password, rol);
		} catch (Exception e) {
			System.out.println("Ha entrado aqui caso 2");
		}
	}

	@When("^\"(.*?)\" y \"(.*?)\" son distintas$")
	public void intento_aniadir_con_el_correcta_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion DiferentesContrasenas \"(.*?)\"$")
	public void se_lanza_la_excepcion_DiferentesContrasenas(String nombre ) throws Throwable {
		Manager.get().eliminarUsuario(nombre);
	}

	// Caso 3
	@Given("^\"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\" y \"(.*?)\" no valida$")
	public void  y_no_valida(String nombre, String email, String password,
			String Confirmacionpassword, String rol) {
		try {
			//Manager.get().register(nombre, email, password, rol);
		} catch (Exception e) {
			System.out.println("Ha entrado aqui caso 3");
		}
	}

	@When("^\"(.*?)\" no cumple requisitos de contrasena$")
	public void no_cumple_requisitos_de_contrasena(String arg1) throws Throwable {	
	}

	@Then("^se lanza la excepcion contrasenaNoValida \"(.*?)\"$")
	public void se_lanza_la_excepcion_contrasenaNoValida(String nombre) throws Throwable {
		Manager.get().eliminarUsuario(nombre);
	}
	
	// Caso 4
		@Given("^\"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\" y \"(.*?)\" email no valido$")
		public void y_email_no_valido(String nombre, String email, String password, 
				String paswordConfirm, String rol) throws Throwable {
			try {
				//Manager.get().register(nombre, email, password, rol);
			} catch (Exception e) {
				System.out.println("Ha entrado aqui caso 4");
			}
		}
		@When("^\"(.*?)\"  no valido$")
		public void no_valido(String arg1) throws Throwable {}
		@Then("^se lanza la excepcion emailNoValido \"(.*?)\"$")
		public void se_lanza_la_excepcion_emailNoValido(String nombre) throws Throwable {
			Manager.get().eliminarUsuario(nombre);
		}

}
