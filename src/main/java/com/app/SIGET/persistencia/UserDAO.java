package com.app.SIGET.persistencia;


import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import com.app.SIGET.dominio.Asistente;
import com.app.SIGET.dominio.Horario;
import com.app.SIGET.dominio.Rol;
import com.app.SIGET.dominio.Admin;
import com.app.SIGET.dominio.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Repository
public final class UserDAO {
	public static final String USUARIO = "users";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";

	private UserDAO() {
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
			if(("ADMIN").equals(document.getString("rol"))) {
				u = new Admin(document.getString(NAME), document.getString(EMAIL), document.getString(PASSWORD));
			} else {
				u = new Asistente(document.getString(NAME), document.getString(EMAIL), document.getString(PASSWORD));
				((Asistente) u).setHorario(Horario.String2Horario(document.getString("horario")));
			}

			usuarios.add(u);
		}

		return usuarios;
	}

	public static void insertar(User user) {
		Document document;
		MongoCollection<Document> coleccion;
		if (user != null) {
			coleccion = AgenteDB.get().getBd(USUARIO);
			document = new Document(NAME, user.getName());
			document.append(EMAIL, user.getEmail());
			document.append(PASSWORD, user.getPassword());
			document.append("rol", user.getRol().toString());
			if(user.getRol().equals(Rol.ASISTENTE)) {
				document.append("horario", ((Asistente)user).getHorario());
			}
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
