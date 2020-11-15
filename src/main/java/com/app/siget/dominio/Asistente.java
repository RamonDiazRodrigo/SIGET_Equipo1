package com.app.siget.dominio;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Asistente extends User {

	private Horario horario;
	protected List<Integer> reunionesPendientes;

	public Asistente(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.reunionesPendientes = new ArrayList<>();
		this.horario = new Horario();
	}

	public List<Integer> getReunionesPendientes() {
		return reunionesPendientes;
	}

	public void setReunionesPendientes(String reunionesPendientes) {
		if(reunionesPendientes !=null) {
			JSONArray jsa = new JSONArray(reunionesPendientes);
			for (int i = 0; i < jsa.length(); i++) {
				this.reunionesPendientes.add(jsa.getInt(i));
			}
		}
		
	}

	public void insertarActividad(Actividad actividad) {

		if (!this.horario.estaOcupado(actividad)) {
			this.horario.insertarActividad(actividad);
		} else {
			System.out.println("mandar error porque ya esta ocupado");
		}

	}
	
	public void insertarReunionPendiente(Actividad a) {
		this.reunionesPendientes.add(a.getId());
		
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	protected JSONObject toJSON() {
		JSONObject jso = new JSONObject();
		JSONArray jsa = new JSONArray();
		jso.put("name", this.getName());
		jso.put("email", this.getEmail());
		jso.put("password", this.getPassword());
		jso.put("rol", this.getRol());

		/*
		 * for (Actividad a : reunionesPendientes) { jsa.put(a.toJSON()); }
		 */
		
		/*
		LocalTime horaIni = LocalTime.of(Integer.parseInt("12"), Integer.parseInt("00"));
		LocalTime horaFin = LocalTime.of(Integer.parseInt("13"), Integer.parseInt("30"));

		Actividad a = new Actividad("Daily", DiaSemana.valueOf("LUNES"), horaIni, horaFin, true);
		jsa.put(a);
		jso.put("reunionesPendientes", jsa);
		*/

		return jso;
	}

	public void quitarReunionPendiente(int id) {
		for (int i = 0; i < this.reunionesPendientes.size(); i++) {
			if (this.reunionesPendientes.get(i)== id) {
				this.reunionesPendientes.remove(i);	
			}
		}
	
		
	}

}
