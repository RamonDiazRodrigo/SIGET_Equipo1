package com.app.siget.dominio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.app.siget.excepciones.AccessNotGrantedException;
import com.app.siget.excepciones.CredencialesInvalidasException;
import com.app.siget.persistencia.ActividadDAO;
import com.app.siget.persistencia.TokenDAO;
import com.app.siget.persistencia.UserDAO;

public class Manager {

	private WebSocketSession session;
	public static final String USUARIOS = "usuarios";

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

				Token t = new Token(name);
				TokenDAO.insert(t);

				JSONObject jso = new JSONObject();
				jso.put("rol", u.getRol());
				jso.put("token", t.getToken());
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
		String pwdEncrypted,pwdUser;
		if (u.getName().equals(name)) {
			pwdEncrypted=u.getPassword();
			pwdUser= encriptarMD5(password);
			if (!(pwdEncrypted.equals(pwdUser))) {

				throw new CredencialesInvalidasException();

			} else {
				System.out.println("Sucessful login");
				aux = true;
			}
		}
		return aux;

	}

	public void register(String name, String email, String password, String rol) {
		if ("ADMIN".equals(rol)) {
			UserDAO.insertar(new Admin(name, email, encriptarMD5(password)));
		} else {
			UserDAO.insertar(new Asistente(name, email, encriptarMD5(password)));
		}

	}

	public JSONObject leerUsuarios() {
		JSONArray jsa = new JSONArray();
		JSONObject jso = new JSONObject();
		List<User> usuarios = UserDAO.leerUsers();

		for (User user : usuarios) {

			jsa.put(user.toJSON());
		}
		jso.put(USUARIOS, jsa);

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
			if (usuario.equals(user.getName()) && "ASISTENTE".equals(user.getRol())) {
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
			if (usuario.equals(u.getName()) && "ASISTENTE".equals(u.getRol())) {
				UserDAO.eliminar(u);
			}
		}
	}

	public void error() {
		// sustituir este metodo por su equivalente de los de arriba
	}

	public JSONObject leer() {
		JSONObject jso = new JSONObject();
		jso.put(USUARIOS, Manager.get().leerAsistentes());
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
		for (Token t : TokenDAO.leerTokensEliminar()) {
			TokenDAO.eliminar(t);
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
				buscarActividades(horario, jsa);
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
					if (a !=null && !contiene(actividades, a)) {
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
			if (nombre.equals(u.getName()) && "ADMIN".equals(u.getRol())) {
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
		Actividad reunionPendiente = new Actividad(nombre, DiaSemana.valueOf(dia), horaIni, horaFin,
				Boolean.parseBoolean(reunion));

		for (int i = 0; i < jsa.length(); i++) {
			for (User u : UserDAO.leerUsers()) {
				if (u.getName().equals(jsa.get(i))) {
					ActividadDAO.insertarReunionPend((Asistente) u, reunionPendiente);

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

	public void modificarUsuario(String nombre, String emailNuevo, String passwordNueva) {
		// Mismo metodo para modificar usuario tanto para Asistente como para Admin

		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(nombre)) {
				u.setEmail(emailNuevo);
				u.setPassword(passwordNueva);
				UserDAO.modificar(u);
			}
		}
	}

	public void ascenderUsuario(String nombre) {
		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(nombre)) {
				Admin user = new Admin(u.getName(), u.getEmail(), u.getPassword());
				UserDAO.modificar(user);
			}
		}
	}

	public JSONArray leerInfoUsuario(String nombre) {
		JSONArray jsa = new JSONArray();
		JSONObject jso = new JSONObject();
		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(nombre)) {
				jsa.put(u.toJSON());
			}
		}
		jso.put(USUARIOS, jsa);

		return jsa;
	}

	public JSONArray cargarReunionesPendientes(String usuario) {
		JSONArray jsa = new JSONArray();
		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(usuario)) {
				for (int id : ((Asistente) u).getReunionesPendientes()) {
					for (Actividad actv : ActividadDAO.leerReuniones()) {
						if (id == actv.getId()) {
							jsa.put(actv.toJSON());
						}

					}
				}

			}
		}
		// jso.put("reunionesPendientes", asistente.getReunionesPendientes());

		return jsa;
	}

	public void aceptarReunion(String usuario, int id) {
		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(usuario)) {
				((Asistente) u).quitarReunionPendiente(id);

				for (Actividad actv : ActividadDAO.leerReuniones()) {
					if (actv.getId() == id) {
						((Asistente) u).insertarActividad(actv);
						UserDAO.modificar(u);

					}
				}

			}
		}
	}

	public void rechazarReunion(String usuario, int id) {
		for (User u : UserDAO.leerUsers()) {
			if (u.getName().equals(usuario)) {
				((Asistente) u).quitarReunionPendiente(id);
				UserDAO.modificar(u);

			}
		}
	}
	
	public void cerrarSesion(String name) {
		TokenDAO.eliminar(new Token(name));
	}

	public void checkAccess(String name, String token, String page) throws AccessNotGrantedException {
		if (token.equals(TokenDAO.getToken(name).getToken())) {

			switch (page) {

			case "admin.html":
				if (!UserDAO.findUser(name).isAdmin()) {
					cerrarSesion(name);
					throw new AccessNotGrantedException();
				}
				break;
			case "gestion.html":
				if (!UserDAO.findUser(name).isAdmin()) {
					cerrarSesion(name);
					throw new AccessNotGrantedException();
				}
				break;
			default:
				if (UserDAO.findUser(name).isAdmin()) {
					cerrarSesion(name);
					throw new AccessNotGrantedException();
				}
				break;

			}

		} else {
			throw new AccessNotGrantedException();
		}

	}
	private static String encriptarMD5(String input){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
			}
		catch (NoSuchAlgorithmException e) {
			 throw new RuntimeException(e);
		}
	}
	

}
