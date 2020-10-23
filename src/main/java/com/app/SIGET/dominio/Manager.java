package com.app.SIGET.dominio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
		if(rol.equals(Rol.ADMIN)) {
			UserDAO.insertar(new Admin(name, email, password));
		} else {
			UserDAO.insertar(new Asistente(name, email, password));
		}
		
	}

	public Object leer() {
		return null;
	}

	public void insertarActividad(String nombre, DayOfWeek dia, LocalTime horaI, LocalTime horaF, List<User> usuarios) {
		
		for(User u: usuarios) {
			ActividadDAO.insertarActividad(u,new Actividad(nombre, dia, horaI, horaF));
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
