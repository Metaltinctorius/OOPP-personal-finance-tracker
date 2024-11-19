package gu.dit213.group28.model.managers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import gu.dit213.group28.model.config.MongoConfig;

public class MongoConnectionManager {

  MongoClient mongoClient;
  MongoDatabase database;

  public MongoConnectionManager(String dbName){
    mongoClient = MongoConfig.getMongoClient();
    database = mongoClient.getDatabase(dbName);
  }

  public MongoDatabase getDatabase(){
    return database;
  }
  public void close(){
    mongoClient.close();
  }

}
