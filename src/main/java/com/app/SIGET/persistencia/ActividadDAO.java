package com.app.SIGET.persistencia;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Repository;
import com.app.SIGET.dominio.Asistente;
import com.app.SIGET.dominio.Horario;
import com.app.SIGET.dominio.Actividad;
import com.app.SIGET.dominio.Admin;
import com.app.SIGET.dominio.Rol;
import com.app.SIGET.dominio.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Repository
public final class ActividadDAO {
	public static final String ACTS = "actividades";
	public static final String USUARIO = "users";

	private ActividadDAO() {
		super();
	}

	public static List<User> leerUsers() {
		ArrayList<User> usuarios = new ArrayList<>();
		Document document;
		User u;
		MongoCollection<Document> coleccion = AgenteDB.get().getBd(USUARIO);
		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			if(document.getString("rol").equals("ADMIN")) {
				u = new Admin(document.getString("name"), document.getString("email"), document.getString("password"));
			} else {
				u = new Asistente(document.getString("name"), document.getString("email"), document.getString("password"));
				((Asistente) u).setHorario(Horario.String2Horario(document.getString("horario")));
			}

			usuarios.add(u);
		}

		return usuarios;
	}

	public static List<Actividad> leerActividades() {
		ArrayList<Actividad> actividades = new ArrayList<>();
		Document document;
		Actividad act;
		MongoCollection<Document> coleccion = AgenteDB.get().getBd(ACTS);
		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			LocalTime horaI = LocalTime.of(document.getInteger("horaI",0), document.getInteger("minutosI",0));
			LocalTime horaF = LocalTime.of(document.getInteger("horaF",0), document.getInteger("minutosF",0));
			act = new Actividad(document.getInteger("id",-1), document.getString("name"), DayOfWeek.valueOf(document.getString("dia")),
					horaI,horaF);
			actividades.add(act);
		}
		return actividades;
	}

	public static void insertar(User user) {
		Document document;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = AgenteDB.get().getBd(USUARIO);
			document = new Document("name", user.getName());
			document.append("email", user.getEmail());
			document.append("password", user.getPassword());
			document.append("rol", user.getRol().toString());
			coleccion.insertOne(document);
		}
		
	}
	
	/*
	 * public static void eliminar(User user, Actividad actividad) { Document
	 * document;
	 * 
	 * MongoCollection<Document> coleccion;
	 * 
	 * if (user != null) { coleccion = AgenteDB.get().getBd(USUARIO); document = new
	 * Document("name", user.getName()); } else { coleccion =
	 * AgenteDB.get().getBd(REU); document = new Document("name",
	 * actividad.getName()); }
	 * 
	 * coleccion.findOneAndDelete(document);
	 * 
	 * }
	 * 
	 */

}
