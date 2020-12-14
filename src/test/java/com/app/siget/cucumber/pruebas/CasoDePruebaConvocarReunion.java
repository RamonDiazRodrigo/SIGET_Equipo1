package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalTime;

import com.app.siget.dominio.Actividad;
import com.app.siget.dominio.DiaSemana;
import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.ActividadDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaConvocarReunion {	
	
	@Given("^los datos \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" y \"(.*?)\" involucrados$")
	public void y_involucrados(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario, String reunion) throws NumberFormatException {
		Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuario, "true");
		try {
		Manager.get().insertarActividad(nombre, dia, horaI, minutosI, horaF, minutosF, usuario, reunion);
		} catch (Exception e) {
			
		}
	}
	
	@Then("^Crea una reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" y \"(.*?)\" y la aniade a reuniones pendientes si no tienen ese horario ocupado$")
	public void crea_una_reunion_y_y_la_aniade_a_reuniones_pendientes_si_no_tienen_ese_horario_ocupado(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuarios) throws Throwable {
		assertEquals(1,ActividadDAO.leerActividades(usuarios).size());
		LocalTime horaIni = LocalTime.of(Integer.parseInt("12"), Integer.parseInt("00"));
		LocalTime horaFin = LocalTime.of(Integer.parseInt("13"), Integer.parseInt("00"));
		Actividad act = new Actividad(nombre, DiaSemana.valueOf("LUNES"), horaIni, horaFin,true);
		ActividadDAO.eliminar(act);
	}
	
	//Escenario 2
	@Given("^los datos \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\"$")
	public void los_datos(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF, String usuarios) throws Throwable {
		try {
			//probamos a insertar la misma del caso 1
			Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuarios, "true");
		} catch(Exception e) {
		}
		
	}

	@When("^ya existe \"(.*?)\"$")
	public void ya_existe(String arg1) throws Throwable {
	    
	}

	@Then("^se lanza la excepcion ya existe reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" \"(.*?)\"$")
	public void se_lanza_la_excepcion_ya_existe_reunion(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	}
	
	//Escenario 3
	@Given("^la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\"$")
	public void la_reunion(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF, String usuarios) throws Throwable {
		try {
			//probamos a insertar la reunion
			Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuarios, "true");
		} catch(Exception e) {
		
		}
	}

	@When("^el dia no existe$")
	public void el_dia_no_existe() throws Throwable {
	    
	}

	@Then("^se lanza la excepcion dia no existe para la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" \"(.*?)\"$")
	public void se_lanza_la_excepcion_dia_no_existe_para_la_reunion(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	}
	
	
	//Escenario 4
	@Given("^la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" con la hora incial mal$")
	public void la_reunion_con_la_hora_incial_mal(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF, String usuarios) throws Throwable {
		try {
			//probamos a insertar la reunion
			Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuarios, "true");
		} catch(Exception e) {
			System.out.println("La hora inicial no es correcto");
		}
	}


	@Then("^se lanza la excepcion hora inicial no existe$")
	public void se_lanza_la_excepcion_hora_inicial_no_existe(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	}
	
	//Escenario 5
	@Given("^la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" con la hora final mal$")
	public void la_reunion_con_la_hora_final_mal(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF, String usuarios) throws Throwable {
		try {
			//probamos a insertar la reunion
			Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, usuarios, "true");
		} catch(Exception e) {
			
		}
	}


	@Then("^se lanza la excepcion hora final no existe para la reunion$")
	public void se_lanza_la_excepcion_hora_final_no_existe(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	}
	@Given("^los datos \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" y \"(.*?)\" involucrados$")
	public void los_datos_y_involucrados(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		try {
			//probamos a insertar la reunion
			Manager.get().convocarReunion(arg1, arg2, arg3, arg4, arg5, arg6, arg7, "true");
		} catch(Exception e) {
		
		}
	    
	}

   

	@When("^la hora inicial no existe$")
	public void la_hora_inicial_no_existe() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^se lanza la excepcion hora inicial no existe para la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" \"(.*?)\"$")
	public void se_lanza_la_excepcion_hora_inicial_no_existe_para_la_reunion(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	    
	}
	@Then("^se lanza la excepcion hora final no existe para la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\" \"(.*?)\"$")
	public void se_lanza_la_excepcion_hora_final_no_existe_para_la_reunion(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Throwable {
		assertNotEquals(1,ActividadDAO.leerActividades(arg7).size());
	}
	@When("^la hora final no existe$")
	public void la_hora_final_no_existe() throws Throwable {
	    
	   
	}

	
}