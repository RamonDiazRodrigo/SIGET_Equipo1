package com.app.SIGET.dominio;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.app.SIGET.excepciones.CredencialesInvalidasException;
import com.app.SIGET.persistencia.ActividadDAO;
import com.app.SIGET.persistencia.UserDAO;

public class Manager {

	private WebSocketSession session;

	public Manager() {
		// Metodo constructor vacio (no hay atributos)
	}

	private static class ManagerHolder {
		private static Manager singleton = new Manager();
	}

	public static Manager get() {
		return ManagerHolder.singleton;
	}

	public void login(String name, String password) throws Exception {
		boolean login = false;

		ArrayList<User> usuarios = (ArrayList<User>) UserDAO.leerUsers();
		for (User u : usuarios) {
			login = checkCredenciales(u, name, password);
			if (login) {
				JSONObject jso = new JSONObject();
				jso.put("rol", u.getRol().toString());
				if (this.session != null) {
					this.session.sendMessage(new TextMessage(jso.toString()));
				}
				break;
			}
		}
		if (!login) {
			throw new CredencialesInvalidasException();
		}

	}

	public boolean checkCredenciales(User u, String name, String password) throws Exception {
		boolean aux = false;
		if (u.getName().equals(name)) {
			if (!(u.getPassword().equals(password))) {

				throw new CredencialesInvalidasException();

			} else {
				System.out.println("Sucessful login");
				aux = true;
			}
		}
		return aux;

	}

	public void register(String name, String email, String password, String rolS) {
		Rol rol = Rol.valueOf(rolS);
		if (rol == Rol.ADMIN) {
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

	public JSONArray leerAsistentes() {
		JSONArray jsa = new JSONArray();
		List<User> usuarios = UserDAO.leerUsers("ASISTENTE");

		for (User user : usuarios) {
			jsa.put(user.toJSON());
		}

		return jsa;

	}

	public JSONArray leerActividades() {
		JSONArray jsa = new JSONArray();
		List<Actividad> actividades = ActividadDAO.leerActividades();

		if (!actividades.isEmpty()) {
			for (Actividad act : actividades) {
				jsa.put(act.toJSON());
			}
		}

		return jsa;

	}

	public void insertarActividad(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario) {

		List<User> users = UserDAO.leerUsers();

		LocalTime horaIni = LocalTime.of(Integer.parseInt(horaI), Integer.parseInt(minutosI));
		LocalTime horaFin = LocalTime.of(Integer.parseInt(horaF), Integer.parseInt(minutosF));

		for (User user : users) {
			if (usuario.equals(user.getName()) && user.getRol() == Rol.ASISTENTE) {
				ActividadDAO.insertarActividad((Asistente) user,
						new Actividad(nombre, DiaSemana.valueOf(dia), horaIni, horaFin));
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

	public JSONObject leer() {
		JSONObject jso = new JSONObject();
		jso.put("usuarios", Manager.get().leerAsistentes());
		jso.put("actividades", Manager.get().leerActividades());

		return jso;
	}

	public void eliminarTests() {
		for (User u : UserDAO.leerUsers()) {
			if ("nombre".equals(u.getName())) {
				UserDAO.eliminar(u);
			}
		}
		for (Actividad a : ActividadDAO.leerActividades()) {
			if ("nombre periodo no laborable".equals(a.getName())) {
				ActividadDAO.eliminar(a);
			}
		}
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

}
