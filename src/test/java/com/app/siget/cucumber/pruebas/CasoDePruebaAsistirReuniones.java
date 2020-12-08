package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Asistente;
import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
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
		User u = UserDAO.findUser(usuario);
		int[][] horario = ((Asistente) u).getHorario().getMatrizHorario();
		boolean reunion = false;
		for(int i =0; i< horario.length;i++) {
			for(int j =0; j<horario[i].length;j++) {
				if(horario[i][j] == Integer.parseInt(id)) {
					reunion = true;
				}
			}
		}
		assertEquals(true,reunion);
	}

	@Given("^el usuario \"([^\"]*)\" acepta la reunion \"([^\"]*)\"$")
	public void el_usuario_rechaza_la_reunion(String nombre, String id) throws FranjaHorariaOcupadaException {
		Manager.get().rechazarReunion(nombre, Integer.parseInt(id));
	}

	@Then("^el usuario \"([^\"]*)\" tiene la reunion \"([^\"]*)\" en su agenda$")
	public void el_usuario_no_tiene_la_reunion_en_su_agenda(String usuario, String id) throws Throwable {
		User u = UserDAO.findUser(usuario);
		int[][] horario = ((Asistente) u).getHorario().getMatrizHorario();
		boolean reunion = false;
		for(int i =0; i< horario.length;i++) {
			for(int j =0; j<horario[i].length;j++) {
				if(horario[i][j] == Integer.parseInt(id)) {
					reunion = true;
				}
			}
		}
		assertEquals(false,reunion);
	}

}