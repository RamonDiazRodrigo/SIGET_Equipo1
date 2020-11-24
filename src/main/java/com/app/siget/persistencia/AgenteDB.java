package com.app.siget.persistencia;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AgenteDB {

	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase database;
	// assumes the current class is called MyLogger

	public AgenteDB() {

		uri = new MongoClientURI(
				"mongodb://david:david123@cluster0" + "-shard-00-00.xmqnt.mongodb.net:27017,cluster0-shard"
						+ "-00-01.xmqnt.mongodb.net:27017,cluster0-shard-00-02."
						+ "xmqnt.mongodb.net:27017/Equipo1?ssl=true&replicaSet=Cluster0"
						+ "-shard-0&authSource=admin&retryWrites=true&w=majority");
		mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase("Equipo1");

	}

	private static class BrokerHolder {
		private static AgenteDB singleton = new AgenteDB();
	}

	public static AgenteDB get() {
		return BrokerHolder.singleton;

	}

	public MongoCollection<Document> getBd(String collection) {
		if (this.database == null) {
			AgenteDB.get();
			return getBd(collection);
		} else {
			return this.database.getCollection(collection);
		}
	}

}
