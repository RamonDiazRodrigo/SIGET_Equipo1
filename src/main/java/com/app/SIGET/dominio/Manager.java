package com.app.SIGET.dominio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
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

	public JSONArray leerReuniones() {
		JSONArray jsa = new JSONArray();
		List<Actividad> actividades = ActividadDAO.leerReuniones();
		if (!actividades.isEmpty()) {
			for (Actividad act : actividades) {
				jsa.put(act.toJSON());
			}
		}

		return jsa;

	}

	public void insertarActividad(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String usuario, String reunion) {

		List<User> users = UserDAO.leerUsers();

		LocalTime horaIni = LocalTime.of(Integer.parseInt(horaI), Integer.parseInt(minutosI));
		LocalTime horaFin = LocalTime.of(Integer.parseInt(horaF), Integer.parseInt(minutosF));
		boolean reunionB = Boolean.parseBoolean(reunion);

		for (User user : users) {
			if (usuario.equals(user.getName()) && user.getRol() == Rol.ASISTENTE) {
				ActividadDAO.insertarActividad((Asistente) user,
						new Actividad(nombre, DiaSemana.valueOf(dia), horaIni, horaFin, reunionB));
			}
		}
	}

	public void actualizar(String string, boolean boolean1) {
		// sustituir este metodo por su equivalente de los de arriba
	}

	public void eliminarUsuario(String usuario) {
		for (User u : UserDAO.leerUsers()) {
			if (usuario.equals(u.getName()) && Rol.ASISTENTE==u.getRol()) {
				UserDAO.eliminar(u);
			}
		}
	}

	public void error() {
		// sustituir este metodo por su equivalente de los de arriba
	}

	public JSONObject leer() {
		JSONObject jso = new JSONObject();
		jso.put("usuarios", Manager.get().leerAsistentes());
		jso.put("actividades", Manager.get().leerReuniones());

		return jso;
	}

	public void eliminarTests() {
		for (User u : UserDAO.leerUsers()) {
			if ("nombre".equals(u.getName())) {
				UserDAO.eliminar(u);
			}
		}
		for (Actividad a : ActividadDAO.leerReuniones()) {
			if ("nombre periodo no laborable".equals(a.getName())) {
				ActividadDAO.eliminar(a);
			}
		}
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public JSONObject leerActividades(String nombre) {
		JSONObject jso = new JSONObject();
		JSONArray jsa = new JSONArray();
		int[][] horario;

		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(nombre)) {
				horario = ((Asistente) u).getHorario().getMatrizHorario();
				jsa = buscarActividades(horario, jsa);

			}
		}
		jso.put("actividades", jsa);
		return jso;
	}

	// Este metodo encuentra las actividades que estan en el horario del usuario
	private static JSONArray buscarActividades(int[][] horario, JSONArray jsa) {
		Actividad a;
		List<Actividad> actividades = new ArrayList<>();
		for (int i = 0; i < horario.length; i++) {
			for (int j = 0; j < horario[0].length; j++) {
				if (horario[i][j] != 0) {
					a = ActividadDAO.leerActividad(horario[i][j]);
					if (!contiene(actividades, a)) {
						jsa.put(a.toJSON());
						actividades.add(a);
					}
				}
			}
		}
		return jsa;
	}

	private static boolean contiene(List<Actividad> actividades, Actividad a) {
		for (Actividad b : actividades) {
			if (b.getId() == a.getId()) {
				return true;
			}
		}

		return false;
	}

	public boolean isAdmin(String nombre) {
		for (User u : UserDAO.leerUsers()) {
			if (nombre.equals(u.getName()) && Rol.ADMIN.equals(u.getRol())) {
				return true;
			}
		}
		return false;
	}

	public void convocarReunion(String nombre, String dia, String horaI, String minutosI, String horaF, String minutosF,
			String usuarios, String reunion) {

		JSONArray jsa = new JSONArray(usuarios);
		LocalTime horaIni = LocalTime.of(Integer.parseInt(horaI), Integer.parseInt(minutosI));
		LocalTime horaFin = LocalTime.of(Integer.parseInt(horaF), Integer.parseInt(minutosF));

		for (int i = 0; i < jsa.length(); i++) {
			for (User u : UserDAO.leerUsers()) {
				if (u.getName().equals(jsa.get(i))) {
					((Asistente) u).insertarReunionPendiente(new Actividad(nombre, DiaSemana.valueOf(dia), horaIni,
							horaFin, Boolean.parseBoolean(reunion)));
				}
			}

		}

	}

	// Este metodo comprueba si la reunion que se quiere convocar se solapa con
	// otras actividades de usuarios. Devuelve el listado de usuarios disponibles
	public JSONArray usuariosDisponibles(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF) {
		JSONArray jsa = new JSONArray();
		LocalTime horaIni = LocalTime.of(Integer.parseInt(horaI), Integer.parseInt(minutosI));
		LocalTime horaFin = LocalTime.of(Integer.parseInt(horaF), Integer.parseInt(minutosF));

		for (User u : UserDAO.leerUsers()) {
			if (!isAdmin(u.getName()) && !((Asistente) u).getHorario()
					.estaOcupado(new Actividad(nombre, DiaSemana.valueOf(dia), horaIni, horaFin, true))) {

				jsa.put(u.toJSON());
			}

		}

		return jsa;
	}

//Este metodo encuentra las actividades que estan en el horario del asistente y que estan en la base de datos
	/*
	 * private static JSONArray encontrarActividades(JSONArray jsa, int[][] aux, int
	 * i, int j) { boolean repetido = false; for (Actividad act :
	 * ActividadDAO.leerActividades()) { if (act.getId() == aux[i][j]) { repetido =
	 * actividadRepetida(jsa, act);
	 * 
	 * if (!repetido) { jsa.put(act.toJSON()); }
	 * 
	 * } } return jsa;
	 * 
	 * }
	 * 
	 * 
	 * private static boolean actividadRepetida(JSONArray jsa, Actividad act) { for
	 * (int j2 = 0; j2 < jsa.length(); j2++) { if
	 * (jsa.getJSONObject(j2).getInt("id") == (act.getId())) { return true; } }
	 * return false; }
	 */

}
