package com.app.siget.ws;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.app.siget.dominio.Manager;

@Component
public class SpringWebSocket extends TextWebSocketHandler {

	private static final String NOMBRE = "nombre";
	private static final String TYPE = "type";
	private static final String VISTA = "vista";
	private static final String HF = "horaFinal";
	private static final String DIA = "dia";
	private static final String HI = "horaInicio";
	private static final String MF = "minutoFinal";
	private static final String MI = "minutoInicio";

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Manager.get().setSession(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		JSONObject jso = new JSONObject(message.getPayload().toString());

		if ("ready".equals(jso.getString(TYPE))) {
			Manager.get().eliminarTests();
		}
		if ("convocarReunion".equals(jso.getString(TYPE))) {
			Manager.get().convocarReunion(jso.getString(NOMBRE), jso.getString(DIA), jso.getString(HI),
					jso.getString(MI), jso.getString(HF), jso.getString(MF),
					jso.get("usuarios").toString(), "true");
		}
		if ("check".equals(jso.getString(TYPE))) {
			session.sendMessage(new TextMessage(Manager.get()
					.usuariosDisponibles(jso.getString(NOMBRE), jso.getString(DIA), jso.getString(HI),
							jso.getString(MI), jso.getString(HF), jso.getString(MF))
					.toString()));
		}

		if ("leer".equals(jso.getString(TYPE))) {
			if (Manager.get().isAdmin(jso.getString(NOMBRE)) || "gestionUsuarios".equals(jso.getString(VISTA))) {
				session.sendMessage(new TextMessage(Manager.get().leer().toString()));
			} else {
				session.sendMessage(
						new TextMessage(Manager.get().leerActividades((String) jso.get(NOMBRE)).toString()));
			}
		}

		if ("insertar".equals(jso.getString(TYPE))) {

			Manager.get().insertarActividad((String) jso.get(NOMBRE), jso.getString(DIA), jso.getString(HI),
					jso.getString(MI), jso.getString(HF), jso.getString(MF),
					jso.getString("usuarios"), "false");
		}

		if ("eliminar".equals(jso.getString(TYPE))) {
			Manager.get().eliminarUsuario((String) jso.get(NOMBRE));
		}

		if ("Register".equals(jso.getString(TYPE))) {
			Manager.get().register((String) jso.get(NOMBRE), jso.getString("email"), jso.getString("pwd1"),
					jso.getString("rol"));
		}

		if ("infoUsuarios".equals(jso.getString(TYPE))) {
			session.sendMessage(new TextMessage(Manager.get().leer().toString()));
		}

		if ("modificar".equals(jso.getString(TYPE))) {
			// Misma condicion para modificar usuario tanto para Asistente como para Admin
			Manager.get().modificarUsuario(jso.getString(NOMBRE), jso.getString("email"), jso.getString("pwd"));
		}

		if ("ascender".equals(jso.getString(TYPE))) {
			Manager.get().ascenderUsuario(jso.getString(NOMBRE));
		}

		if ("aceptarReunion".equals(jso.getString(TYPE))) {
			Manager.get().aceptarReunion(jso.getString(NOMBRE), jso.getInt("id"));
			session.sendMessage(
					new TextMessage(Manager.get().cargarReunionesPendientes(jso.getString(NOMBRE)).toString()));
		}
		if ("rechazarReunion".equals(jso.getString(TYPE))) {
			Manager.get().rechazarReunion(jso.getString(NOMBRE), jso.getInt("id"));
			session.sendMessage(
					new TextMessage(Manager.get().cargarReunionesPendientes(jso.getString(NOMBRE)).toString()));
		}
		if ("reunionesPendientes".equals(jso.getString(TYPE))) {
			session.sendMessage(
					new TextMessage(Manager.get().cargarReunionesPendientes(jso.getString(NOMBRE)).toString()));

		}
	}
}
