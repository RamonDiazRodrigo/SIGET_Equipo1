package com.app.SIGET.persistencia;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.app.SIGET.dominio.DiaSemana;
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
			act = new Actividad(document.getInteger("id",-1), document.getString("name"), DiaSemana.valueOf(document.getString("dia")),
					horaI,horaF);
			actividades.add(act);
		}
		return actividades;
	}

	public static void insertarActividad(Asistente user, Actividad actividad) {
		Document document, document2;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = AgenteDB.get().getBd(ACTS);
			document = new Document("name", actividad.getName());
			document.append("dia", actividad.getDia().toString());
			document.append("horaI", actividad.getHoraI().getHour());
			document.append("minutosI", actividad.getHoraI().getMinute());
			document.append("horaF", actividad.getHoraF().getHour());
			document.append("minutosF", actividad.getHoraF().getMinute());
			coleccion.insertOne(document);
			coleccion = AgenteDB.get().getBd(USUARIO);
			document = new Document("name", user.getName());
			document2 = new Document("name", user.getName());
			document2.append("email", user.getEmail());
			document2.append("password", user.getPassword());
			document2.append("rol", user.getRol().toString());
			user.insertarActividad(actividad);
			document2.append("horario", user.getHorario());
			coleccion.findOneAndReplace(document, document2);
		} else {
			System.out.println("Mandar un error de que el user es null");
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
