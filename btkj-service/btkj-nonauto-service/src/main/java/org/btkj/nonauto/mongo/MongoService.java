package org.btkj.nonauto.mongo;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;

@Service("mongoService")
public class MongoService {

	private String username;
	private String password;
	private String dbName;
	private MongoClient mongo;
	private MongoDatabase db;
	
	@PostConstruct
	private void init() {
		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
		this.mongo = new MongoClient();
		this.db = mongo.getDatabase("");
	}
}
