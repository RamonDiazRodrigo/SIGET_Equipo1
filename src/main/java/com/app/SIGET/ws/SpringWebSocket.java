package com.app.SIGET.ws;

import java.time.LocalTime;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.app.SIGET.dominio.DiaSemana;
import com.app.SIGET.dominio.Manager;

@Component
public class SpringWebSocket extends TextWebSocketHandler {

	private static final String NOMBRE = "nombre";
	private static final String TYPE = "type";

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		JSONObject jso = new JSONObject(message.getPayload().toString());
		if ("ready".equals(jso.getString(TYPE))) {
			session.sendMessage(new TextMessage(Manager.get().leerUsuarios().toString()));
		}

		if ("insertar".equals(jso.getString(TYPE))) {

			Manager.get().insertarActividad((String) jso.get(NOMBRE),DiaSemana.valueOf(jso.getString("dia")),
					LocalTime.of(jso.getInt("horaInicio"),jso.getInt("minutoInicio")), LocalTime.of(jso.getInt("horaFinal"),
							jso.getInt("minutoFinal"))
							,jso.getString("usuarios"));
			session.sendMessage(new TextMessage(Manager.get().leerActividades().toString()));
		}
		
		

		if ("actualizar".equals(jso.getString(TYPE))) {
			Manager.get().actualizar((String) jso.get(NOMBRE), jso.getBoolean("done"));
			//session.sendMessage(new TextMessage(Manager.get().leer().toString()));
		}

		if ("eliminar".equals(jso.getString(TYPE))) {
			Manager.get().eliminar((String) jso.get(NOMBRE));
			//session.sendMessage(new TextMessage(Manager.get().leer().toString()));
		}

	}

}
