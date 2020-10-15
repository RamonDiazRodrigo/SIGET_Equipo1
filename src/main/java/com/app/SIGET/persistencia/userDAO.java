package com.app.SIGET.persistencia;

import java.util.ArrayList;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import com.app.SIGET.dominio.User;
import com.app.SIGET.dominio.Reunion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Repository
public class userDAO {

	public static ArrayList<User> leerUsers() {

		ArrayList<User> usuarios = new ArrayList<User>();
		Document document;
		User u;

		MongoCollection<Document> coleccion = agenteDB.get().getBd("users");

		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			u = new User(document.getString("name"), document.getString("email"), document.getString("password"), document.getString("rol"));
			usuarios.add(u);
		}

		return usuarios;
	}

	public static ArrayList<Reunion> leerReuniones() {

		ArrayList<Reunion> reuniones = new ArrayList<Reunion>();
		Document document;
		Reunion r;

		MongoCollection<Document> coleccion = agenteDB.get().getBd("reuniones");

		MongoCursor<Document> iter = coleccion.find().iterator();

		while ((iter.hasNext())) {
			document = iter.next();
			r = new Reunion(document.getString("name"),document.getString("fecha"),document.getString("horaI"),document.getString("horaF"));
			reuniones.add(r);
		}

		return reuniones;
	}

	public static void insertar(User user, Reunion reunion) {
		Document document;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = agenteDB.get().getBd("users");
			document = new Document("name", user.getName());
			document.append("password", user.getPassword());
		} else {
			coleccion = agenteDB.get().getBd("reuniones");
			document = new Document("name", reunion.getNombreReunion());
		}
		coleccion.insertOne(document);

	}

	public static void eliminar(User user, Reunion reunion) {
		Document document;

		MongoCollection<Document> coleccion;

		if (user != null) {
			coleccion = agenteDB.get().getBd("users");
			document = new Document("name", user.getName());
		} else {
			coleccion = agenteDB.get().getBd("reuniones");
			document = new Document("name", reunion.getNombreReunion());
		}

		coleccion.findOneAndDelete(document);

	}

}
