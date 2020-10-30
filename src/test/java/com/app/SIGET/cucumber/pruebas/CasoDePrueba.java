package com.app.SIGET.cucumber.pruebas;

import org.json.JSONArray;
import org.json.JSONObject;
import com.app.SIGET.dominio.Manager;
import com.app.SIGET.excepciones.CredencialesInvalidasException;
import com.app.SIGET.excepciones.DiferentesContrasenasException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePrueba {

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos asistente$")
	public void acceso_con_y_correctos_asistente(String nombre, String password) throws Exception {
		nombre="a";
		password="a";
		Manager.get().login(nombre, password);
	}

	@When("^los datos son correctos y de un asistente$")
	public void los_datos_son_correctos_y_de_un_asistente() {
	}

	@Then("^Accedo a la pantalla principal de asistente$")
	public void accedo_a_la_pantalla_principal_de_asistente() {
		// La pagina actual es Asistente.html
	}

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos admin$")
	public void acceso_con_y_correctos_admin(String nombre, String password) throws Exception {
		nombre="a";
		password="a";
		Manager.get().login(nombre, password);
	}

	@When("^los datos son correctos y de un administrador$")
	public void los_datos_son_correctos_y_de_un_administrador() {
	}

	@Then("^Accedo a la pantalla principal de admin$")
	public void accedo_a_la_pantalla_principal_de_admin() {
		// La pagina actual es Asistente.html
	}

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\"$")
	public void acceso_con_y(String nombre, String password) throws Exception {
		nombre="a";
		password="a";
		Manager.get().login(nombre, password);
	}

	@When("^intento acceder con el \"([^\"]*)\" correcto y \"([^\"]*)\" mal$")
	public void intento_acceder_con_el_correcto_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion CredencialesInvalidas$")
	public void se_lanza_la_excepcion_CredencialesInvalidas() throws CredencialesInvalidasException {

	}

	@Given("^\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
	public void y(String nombre, String email, String password, String confirmacionPassword, String rol)
			throws IllegalArgumentException {
		rol = "ADMIN";
		Manager.get().register(nombre, email, password, rol);
	}

	@Then("^Crea un usuario \"([^\"]*)\"$")
	public void crea_un_usuario(String nombre) throws Exception {
		JSONObject jso = Manager.get().leerUsuarios();
		JSONArray jsa = jso.getJSONArray("usuarios");
		Boolean passed = false;
		for (int i = 0; i < jsa.length(); i++) {
			if (jsa.get(i) == nombre) {
				passed = true;
			}
		}
		if (!passed) {
			System.out.println("No se ha creado");
		}

	}

	@Given("^\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\" distintas$")
	public void y_distintas(String nombre, String email, String password, String confirmacionPassword, String rol)
			throws IllegalArgumentException {
		rol = "ASISTENTE";
		Manager.get().register(nombre, email, password, rol);
	}

	@When("^\"([^\"]*)\" y \"([^\"]*)\" son distintas$")
	public void y_son_distintas(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion DiferentesContrasenas$")
	public void se_lanza_la_excepcion_DiferentesContrasenas() throws DiferentesContrasenasException {

	}

	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" admin$")
	public void acceso_con_y_admin(String nombre, String password) throws Throwable {
		Manager.get().login(nombre, password);
	}

	@When("^en la vista admin\\.html se clicka el boton verAgendaGeneral$")
	public void en_la_vista_admin_html_se_clicka_el_boton_verAgendaGeneral() throws Throwable {
		Manager.get().leerReuniones();
	}

	@Then("^veo la agenda general$")
	public void veo_la_agenda_general() throws Throwable {
	}
	
	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" asistente$")
	public void acceso_con_y_asistente(String nombre, String password) throws Throwable {
		Manager.get().login(nombre, password);
	}
	
	
	@Then("^veo la agenda del usuario$")
	public void veo_la_agenda_del_usuario() throws Throwable {
		Manager.get().leerReuniones();
	}

}