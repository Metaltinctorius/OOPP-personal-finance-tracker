package gu.dit213.group28.model.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {
  private static MongoClient mongoClient;

  static {
    try {
      // Connect to MongoDB running in Docker
      mongoClient = MongoClients.create("mongodb://localhost:27017");
    } catch (Exception e) {
      throw new RuntimeException("Failed to connect to MongoDB", e);
    }
  }

  public static MongoClient getMongoClient() {
    return mongoClient;
  }
}