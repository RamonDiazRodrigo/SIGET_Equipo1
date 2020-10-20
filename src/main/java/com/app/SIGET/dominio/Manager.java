package com.app.SIGET.dominio;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.SIGET.persistencia.userDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import com.app.SIGET.dominio.*;
import com.mongodb.MongoClientURI;






public class Manager {

	private Manager() {
	}

	private static class ManagerHolder {
		static Manager singleton = new Manager();
	}

	public static Manager get() {
		return ManagerHolder.singleton;
	}
	

	public void login(String name, String password) throws Exception {
		try {
			boolean UsrFound=false;
			ArrayList<User> usuarios=userDAO.leerUsers();
			for(User u : usuarios) {
				if(u.getName().equals(name)) {
					UsrFound = true;
					if(!(u.getPassword().equals(password))) {
						throw new Exception("Credenciales inválidas");

					}else {
						System.out.println("Sucessful login");
					}
				}
			}
			if(!UsrFound) {
				throw new Exception("Credenciales inválidas");

			}
			
		}
		catch(Exception e) {
			throw new Exception("Error inesperado");
		}
	}
	public void register(String name, String email, String password, String rol) throws Exception {
		
		userDAO.insertar(new User(name,email,password,rol),null); 
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


	public static void error() {
		// TODO Auto-generated method stub
		
	}
}