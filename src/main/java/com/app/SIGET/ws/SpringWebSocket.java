package com.app.SIGET.ws;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.app.SIGET.dominio.Manager;

@Component
public class SpringWebSocket extends TextWebSocketHandler {

	private static final String NOMBRE = "nombre";
	private static final String TYPE = "type";
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	Manager.get().setSession(session);
    }	

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		JSONObject jso = new JSONObject(message.getPayload().toString());
		if ("ready".equals(jso.getString(TYPE))) {
			Manager.get().eliminarTests();
			session.sendMessage(new TextMessage(Manager.get().leer().toString()));
		}

		if ("insertar".equals(jso.getString(TYPE))) {

			Manager.get().insertarActividad((String)jso.get(NOMBRE),jso.getString("dia"),jso.getString("horaInicio")
					,jso.getString("minutoInicio"), jso.getString("horaFinal"),jso.getString("minutoFinal"),jso.getString("usuarios"),"false");
			session.sendMessage(new TextMessage(Manager.get().leerReuniones().toString()));
		}
		
		if ("leerActividades".equals(jso.getString(TYPE))) {
			session.sendMessage(new TextMessage(Manager.get().leerActividades((String) jso.get(NOMBRE))));
		}

		if ("eliminar".equals(jso.getString(TYPE))) {
			Manager.get().eliminar((String) jso.get(NOMBRE));
			//session.sendMessage(new TextMessage(Manager.get().leer().toString()));

		}
	}

}