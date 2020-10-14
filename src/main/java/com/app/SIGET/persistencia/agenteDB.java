package com.app.SIGET.persistencia;

import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class agenteDB {

	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collectionUsers;
	private MongoCollection<Document> collectionReuniones;

	private agenteDB() {
		try {
			uri = new MongoClientURI(
					"mongodb://david:david123@cluster0-shard-00-00."
					+ "xmqnt.mongodb.net:27017,cluster0-shard-00-01.xmqnt"
					+ ".mongodb.net:27017,cluster0-shard-00-02.xmqnt.mongodb."
					+ "net:27017/Equipo1?ssl=true&replicaSet=Cluster0-shard-0&"
					+ "authSource=admin&retryWrites=true&w=majority");
			mongoClient = new MongoClient(uri);
			database = mongoClient.getDatabase("Equipo1");
			collectionUsers = database.getCollection("users");
			collectionReuniones = database.getCollection("reuniones");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class BrokerHolder {
		static agenteDB singleton = new agenteDB();
	}

	public static agenteDB get() {
		return BrokerHolder.singleton;
	}

	public MongoCollection<Document> getBd(String collection) {
		if (this.database != null) {
			this.get();
		}
		if ("users".equals(collection)) {
			return this.collectionUsers;
		} else {
			return this.collectionReuniones;
		}
	}

}