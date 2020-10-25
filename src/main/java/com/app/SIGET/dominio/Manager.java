package com.app.SIGET.dominio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.SIGET.excepciones.CredencialesInvalidasException;
import com.app.SIGET.persistencia.*;

public class Manager {

	public Manager() {
		// Metodo constructor vacio (no hay atributos)
	}

	private static class ManagerHolder {
		private static Manager singleton = new Manager();
	}

	public static Manager get() {
		return ManagerHolder.singleton;
	}

	public void login(String name, String password) {
		try {
			ArrayList<User> usuarios = (ArrayList<User>) UserDAO.leerUsers();
			for (User u : usuarios) {
				checkCredenciales(u, name, password);
			}
		} catch (CredencialesInvalidasException e) {
			e.printStackTrace();
		}
	}

	public void checkCredenciales(User u, String name, String password) throws CredencialesInvalidasException {
		if (u.getName().equals(name)) {
			if (!(u.getPassword().equals(password))) {
				throw new CredencialesInvalidasException();
			} else {
				System.out.println("Sucessful login");
			}
		}
	}

	public void register(String name, String email, String password, Rol rol) {
		if (rol.equals(Rol.ADMIN)) {
			UserDAO.insertar(new Admin(name, email, password));
		} else {
			UserDAO.insertar(new Asistente(name, email, password));
		}

	}
	public JSONObject leerUsuarios() {
		JSONArray jsa = new JSONArray();
		JSONObject jso = new JSONObject();
		List<User> usuarios = UserDAO.leerUsers();


		for (User user : usuarios) {
			
			jsa.put(user.toJSON());
		}
		jso.put("usuarios", jsa);

		return jso;

	}

	public JSONObject leerActividades() {
		JSONArray jsa = new JSONArray();
		JSONObject jso = new JSONObject();
		List<Actividad> actividades = ActividadDAO.leerActividades();

		if (!actividades.isEmpty()) {
			for (Actividad act : actividades) {
				jsa.put(act.toJSON());
			}
		}

		jso.put("actividades", jsa);

		return jso;

	}

	public void insertarActividad(String nombre, DayOfWeek dia, LocalTime horaI, LocalTime horaF, JSONArray usuarios) {

		List<User> users = UserDAO.leerUsers();
		for (User user : users) {
			for (int i = 0; i < users.size(); i++) {
				if (usuarios.getString(i).equals(user.getName())) {
					//ActividadDAO.insertarActividad(user, new Actividad(nombre, dia, horaI, horaF));
				}
			}
		}

	}

	public void actualizar(String string, boolean boolean1) {
		// sustituir este metodo por su equivalente de los de arriba
	}

	public void eliminar(String string) {
		// sustituir este metodo por su equivalente de los de arriba
	}

	public void error() {
		// sustituir este metodo por su equivalente de los de arriba
	}

}
