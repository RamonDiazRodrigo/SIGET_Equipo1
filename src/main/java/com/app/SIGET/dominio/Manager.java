package com.app.SIGET.dominio;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



public class Manager {

	private Manager() {
	}

	private static class ManagerHolder {
		static Manager singleton = new Manager();
	}

	public static Manager get() {
		return ManagerHolder.singleton;
	}
/*
	public void insertar(String nombre) {

		TareaDAO.insertar(nombre);

	}

	public void actualizar(String nombre, boolean done) {

		TareaDAO.actualizar(nombre, done);

	}

	public void eliminar(String nombre) {

		TareaDAO.eliminar(nombre);

	}

	public JSONObject leer() {

		JSONArray jsa = new JSONArray();
		JSONObject jso = new JSONObject();
		ArrayList<Tarea> tareas = TareaDAO.leer();

		if (!tareas.isEmpty()) {
			for (Tarea t : tareas) {
				jsa.put(t.toJSON());
			}
		}

		jso.put("tareas", jsa);

		return jso;

	}
*/

	public Object leer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertar(String string) {
		// TODO Auto-generated method stub
		
	}

	public void actualizar(String string, boolean boolean1) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(String string) {
		// TODO Auto-generated method stub
		
	}
}