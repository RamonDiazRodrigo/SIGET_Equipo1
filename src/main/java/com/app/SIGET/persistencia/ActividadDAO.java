package com.app.SIGET.persistencia;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.app.SIGET.dominio.DiaSemana;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import com.app.SIGET.dominio.Asistente;
import com.app.SIGET.dominio.Actividad;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Repository
public final class ActividadDAO {
	public static final String ACTS = "actividades";
	public static final String USUARIO = "users";
	public static final String REUNION = "reunion";
	public static final String HORAI = "horaI";
	public static final String HORAF = "horaF";
	public static final String MINUTOSI = "minutosI";
	public static final String MINUTOSF = "minutosf";

	private ActividadDAO() {
		super();
	}

	public static List<Actividad> leerReuniones() {
		ArrayList<Actividad> actividades = new ArrayList<>();
		Document document;
		Actividad act;
		MongoCollection<Document> coleccion = AgenteDB.get().getBd(ACTS);
		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			if (Boolean.TRUE.equals(document.getBoolean(REUNION))) {
				LocalTime horaI = LocalTime.of(document.getInteger(HORAI, 0), document.getInteger(MINUTOSI, 0));
				LocalTime horaF = LocalTime.of(document.getInteger(HORAF, 0), document.getInteger(MINUTOSF, 0));
				act = new Actividad(document.getInteger("id", -1), document.getString("name"),
						DiaSemana.valueOf(document.getString("dia")), horaI, horaF, true);
				actividades.add(act);
			}
		}
		return actividades;
	}

	public static List<Actividad> leerActividades() {
		ArrayList<Actividad> actividades = new ArrayList<>();
		Document document;
		Actividad act;
		MongoCollection<Document> coleccion = AgenteDB.get().getBd(ACTS);
		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			if (Boolean.parseBoolean(document.getString(REUNION))) {
				LocalTime horaI = LocalTime.of(document.getInteger(HORAI, 0), document.getInteger(MINUTOSI, 0));
				LocalTime horaF = LocalTime.of(document.getInteger(HORAF, 0), document.getInteger(MINUTOSF, 0));
				act = new Actividad(document.getInteger("id", -1), document.getString("name"),
						DiaSemana.valueOf(document.getString("dia")), horaI, horaF, true);
				actividades.add(act);
			} else {
				LocalTime horaI = LocalTime.of(document.getInteger(HORAI, 0), document.getInteger(MINUTOSI, 0));
				LocalTime horaF = LocalTime.of(document.getInteger(HORAF, 0), document.getInteger(MINUTOSF, 0));
				act = new Actividad(document.getInteger("id", -1), document.getString("name"),
						DiaSemana.valueOf(document.getString("dia")), horaI, horaF, false);
				actividades.add(act);
			}
		}

		return actividades;
	}

	public static void insertarActividad(Asistente user, Actividad actividad) {
		Document document;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = AgenteDB.get().getBd(ACTS);
			document = new Document("name", actividad.getName());
			document.append("id", actividad.getId());
			document.append("dia", actividad.getDia().toString());
			document.append(HORAI, actividad.getHoraI().getHour());
			document.append(MINUTOSI, actividad.getHoraI().getMinute());
			document.append(HORAF, actividad.getHoraF().getHour());
			document.append(MINUTOSF, actividad.getHoraF().getMinute());
			document.append(REUNION, actividad.isReunion());
			coleccion.insertOne(document);
			coleccion = AgenteDB.get().getBd(USUARIO);
			document = new Document("name", user.getName());
			document.append("email", user.getEmail());
			document.append("password", user.getPassword());
			document.append("rol", user.getRol());
			user.insertarActividad(actividad);
			document.append("horario", user.getHorario().toString());
			UserDAO.eliminar(user);
			coleccion.insertOne(document);
		} else {
			System.out.println("Mandar un error de que el user es null");
		}

	}

	public static void eliminar(Actividad a) {
		Document document;
		MongoCollection<Document> coleccion;

		if (a != null) {
			coleccion = AgenteDB.get().getBd(ACTS);
			document = new Document("name", a.getName());
			coleccion.findOneAndDelete(document);
		}

	}

	public static Actividad leerActividad(int id) {
		
		Document document;
		Actividad act=null;
		MongoCollection<Document> coleccion = AgenteDB.get().getBd(ACTS);
		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			if (id == (document.getInteger("id"))) {

				if (Boolean.TRUE.equals(document.getBoolean(REUNION))) {
					LocalTime horaI = LocalTime.of(document.getInteger(HORAI, 0), document.getInteger(MINUTOSI, 0));
					LocalTime horaF = LocalTime.of(document.getInteger(HORAF, 0), document.getInteger(MINUTOSF, 0));
					act = new Actividad(document.getInteger("id", -1), document.getString("name"),
							DiaSemana.valueOf(document.getString("dia")), horaI, horaF, true);
					
				} else {
					LocalTime horaI = LocalTime.of(document.getInteger(HORAI, 0), document.getInteger(MINUTOSI, 0));
					LocalTime horaF = LocalTime.of(document.getInteger(HORAF, 0), document.getInteger(MINUTOSF, 0));
					act = new Actividad(document.getInteger("id", -1), document.getString("name"),
							DiaSemana.valueOf(document.getString("dia")), horaI, horaF, false);
					
				}
			}
		}
		return act;
	}

	public static void insertarReunionPend(Asistente user, Actividad actividad) {
		Document document;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = AgenteDB.get().getBd(ACTS);
			document = new Document("name", actividad.getName());
			document.append("id", actividad.getId());
			document.append("dia", actividad.getDia().toString());
			document.append(HORAI, actividad.getHoraI().getHour());
			document.append(MINUTOSI, actividad.getHoraI().getMinute());
			document.append(HORAF, actividad.getHoraF().getHour());
			document.append(MINUTOSF, actividad.getHoraF().getMinute());
			document.append(REUNION, actividad.isReunion());
			coleccion.insertOne(document);
			coleccion = AgenteDB.get().getBd(USUARIO);
			document = new Document("name", user.getName());
			document.append("email", user.getEmail());
			document.append("password", user.getPassword());
			document.append("rol", user.getRol());
			user.insertarReunionPendiente(actividad);
			document.append("horario", user.getHorario().toString());
			document.append("reunionesPendientes", user.getReunionesPendientes().toString());
			UserDAO.eliminar(user);
			coleccion.insertOne(document);
		} else {
			System.out.println("Mandar un error de que el user es null");
		}
		
	}

}
