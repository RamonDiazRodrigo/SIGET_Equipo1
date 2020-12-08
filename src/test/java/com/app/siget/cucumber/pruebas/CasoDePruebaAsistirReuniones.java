package com.app.siget.cucumber.pruebas;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.FranjaHorariaOcupadaException;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAsistirReuniones {

	@Given("^el usuario \"([^\"]*)\" acepta la reunion \"([^\"]*)\"$")
	public void el_usuario_acepta_la_reunion(String nombre, String id) throws FranjaHorariaOcupadaException {
		Manager.get().aceptarReunion(nombre, Integer.parseInt(id));
	}

	@Then("^el usuario \"([^\"]*)\" tiene la reunion \"([^\"]*)\" en su agenda$")
	public void el_usuario_tiene_la_reunion_en_su_agenda(String usuario, String id) throws Throwable {
		UserDAO.findUser(usuario)
	}

	@Given("^el usuario \"([^\"]*)\" acepta la reunion \"([^\"]*)\"$")
	public void el_usuario_rechaza_la_reunion(String nombre, String id) throws FranjaHorariaOcupadaException {
		Manager.get().rechazarReunion(nombre, Integer.parseInt(id));
	}

	@Then("^el usuario \"([^\"]*)\" tiene la reunion \"([^\"]*)\" en su agenda$")
	public void el_usuario_no_tiene_la_reunion_en_su_agenda(String usuario, String id) throws Throwable {
		
	}

}