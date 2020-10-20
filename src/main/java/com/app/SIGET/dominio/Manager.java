package com.app.SIGET.dominio;

import java.util.ArrayList;

import com.app.SIGET.excepciones.CredencialesInvalidasException;
import com.app.SIGET.excepciones.ErrorInesperadoException;
import com.app.SIGET.persistencia.userDAO;

public class Manager {

	public Manager() {
		// Metodo constructor vacio (no hay atributos)
	}

	private static class ManagerHolder {
		private static Manager singleton = new Manager();
	}

	public Manager get() {
		return ManagerHolder.singleton;
	}
	

	public void login(String name, String password) throws ErrorInesperadoException {
		try {
			ArrayList<User> usuarios = userDAO.leerUsers();
			for(User u : usuarios) {
				checkCredenciales(u, name, password);
			}
		} catch(Exception e) {
			throw new ErrorInesperadoException(e);
		}
	}
	
	public void checkCredenciales (User u, String name, String password) throws CredencialesInvalidasException {
		if(u.getName().equals(name)) {
			if (!(u.getPassword().equals(password))) {
				throw new CredencialesInvalidasException();
			} else {
				System.out.println("Sucessful login");
			}
		}
	}
	public void register(String name, String email, String password, String rol) {
		userDAO.insertar(new User(name,email,password,rol),null); 
	}

	public Object leer() {
		return null;
	}

	public void insertar(String string) {
		// sustituir este metodo por su equivalente de los de arriba
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
